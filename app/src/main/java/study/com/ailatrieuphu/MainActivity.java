package study.com.ailatrieuphu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStart = null;
    private Button btnExit = null;
    private Button btnCallF;
    private Button btnHelp;
    private Button btnOption;

    AudioClip mAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudio = getAudioClip(R.raw.begin);
        mAudio.loop();

        btnStart = (Button) findViewById(R.id.btnSTART);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnHelp=(Button)findViewById(R.id.btnHelp);
        btnOption=(Button)findViewById(R.id.btnTuyChon);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAudio.stop();
                mAudio = getAudioClip(R.raw.hoi_bat_dau);
                mAudio.play();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Start")
                        .setMessage("Ban da san sang chua ?")
                        .setIcon(R.drawable.icon_question_start)
                        .setPositiveButton("San sang", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAudio.stop();
                                mAudio = getAudioClip(R.raw.begintp);
                                mAudio.play();

                                Intent intent = new Intent(MainActivity.this, Player.class);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton("Bỏ qua",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        });
                builder.show();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.help);
                Button btn_tl_Cancel = (Button) dialog
                        .findViewById(R.id.btn_cancel_Help);
                btn_tl_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setCancelable(true);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.tuy_chon);
                    final Button btn_sound=(Button)dialog.findViewById(R.id.btn_sound);
                    Button btn_tl_Cancel = (Button) dialog
                            .findViewById(R.id.btn_cancel_option);
                    btn_tl_Cancel.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.cancel();

                        }
                    });
                    btn_sound.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if(AudioClip.state_sound==true)
                            {
                                AudioClip.state_sound=false;
                                btn_sound.setText("ÂM THANH TẮT");
                            }
                            else {
                                AudioClip.state_sound=true;
                                btn_sound.setText("ÂM THANH BẬT");
                            }
                        }
                    });
                    dialog.show();

                }
                catch (Exception e) {
                    Log.d("loi btn option", e.getMessage());
                }
            }
        });
    }

    protected AudioClip getAudioClip(int id) {
        return new AudioClip(MainActivity.this, id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("BẠN MUỐN THOÁT KHỎI CHƯƠNG TRÌNH?")
                .setMessage("Cảm ơn bạn đã đến với chúng tôi. Chúc bạn may mắn trong cuộc sống.")
                .setIcon(R.drawable.icon_question_start)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    };
                });

        builder.setNegativeButton("Bo Qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
        return super.onKeyDown(keyCode, event);
    }
}
