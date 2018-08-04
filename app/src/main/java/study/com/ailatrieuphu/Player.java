package study.com.ailatrieuphu;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 07/03/2018.
 */

public class Player extends AppCompatActivity {
    AudioClip playAu;
    MediaPlayer mMediaPlayer;
    TextView Question;
    Button A;
    Button B;
    Button C;
    Button D;
    Button Next;
    String name;
    TrieuPhu[] listQ;
    TrieuPhu[] listQD;
    TrieuPhu[] listQK;
    int lengListQ;
    int vitri = 1;
    Random r;
    int ViTriCauHoi = 1;
    ;
    String dapan;
    AudioClip mPlay;
    Timer t;
    Timer t1;
    String nguoi_tgiup;
    String DA_tgiup;
    Boolean trangThai = true;
    Button btn_callf;
    Button btn_ykKhanG;
    Button btn_50;
    Button[] array_button = new Button[4];
    TextView txtCauHoi, txtDiem;
    ActionBar.LayoutParams LayPa;

    // Handle if use press back key or recent key
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Dialog dialog = new Dialog(Player.this);
            dialog.setCancelable(true);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.nguoi_choi);
            Button btnThoat = (Button) dialog.findViewById(R.id.btnThoat);
            Button btnCalcel = (Button) dialog.findViewById(R.id.cancel);
            btnThoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });
            btnCalcel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        try {
            Question = (TextView) findViewById(R.id.tvQuestion);
            A = (Button) findViewById(R.id.buttonA);
            B = (Button) findViewById(R.id.buttonB);
            C = (Button) findViewById(R.id.buttonC);
            D = (Button) findViewById(R.id.buttonD);
            txtCauHoi = (TextView) findViewById(R.id.txtSoCH);
            txtDiem = (TextView) findViewById(R.id.txtDiem);
            array_button[0] = A;
            array_button[1] = B;
            array_button[2] = C;
            array_button[3] = D;
            btn_ykKhanG = (Button) findViewById(R.id.button_hoi_khangia);
            t = new Timer();

            // Handle data
            saveData();
            LoadData();
            ViewData();

            txtCauHoi.setText("Cau hoi so: " + Integer.toString(vitri));
            txtDiem.setText("Tien : $0");

            btn_callf = (Button) findViewById(R.id.button_callf);
            btn_callf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn_callf.setVisibility(View.INVISIBLE);
                    if (trangThai == true) {
                        mPlay = getAudioClip(R.raw.nguoi_than);
                        mPlay.play();

                        final Dialog dialog = new Dialog(Player.this);
                        dialog.setCancelable(true);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.status_dialog);
                        RadioGroup rgStatus = (RadioGroup) dialog
                                .findViewById(R.id.radioGroup1);
                        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                switch (i) {
                                    case R.id.rd_billGate:
                                        nguoi_tgiup = "Bill Gate";
                                        Toast.makeText(Player.this, "Ban chon " + nguoi_tgiup + "?", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.rd_billladen:
                                        nguoi_tgiup = " Bill Laden ";
                                        Toast.makeText(Player.this,
                                                "Bạn chọn Bill Laden?",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.rd_obama:
                                        nguoi_tgiup = " Obama ";
                                        Toast.makeText(Player.this,
                                                "Bạn chọn Obama?",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.rd_steve:
                                        nguoi_tgiup = " Steve Jobs ";
                                        Toast.makeText(Player.this,
                                                "Bạn chọn Steve Jobs",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                        Button btnChon = (Button) dialog
                                .findViewById(R.id.btn_chon_tro_giup);
                        btnChon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Player.this);
                                builder.setIcon(R.drawable.icon_question_start)
                                        .setTitle("Dap ab tro giup")
                                        .setMessage("Ban da chon"
                                                        + nguoi_tgiup
                                                        + "."
                                                        + nguoi_tgiup
                                                        + "Khuyen ban chon dap an:  "
                                                        + listQ[ViTriCauHoi].QT.toString().toUpperCase());

                                builder.setNegativeButton("Bỏ qua",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                            }
                                        });
                                builder.show();
                                dialog.cancel();
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {

        }

        btn_50 = (Button) findViewById(R.id.button50_50);
        try {
            btn_50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn_50.setVisibility(View.INVISIBLE);
                    final int dapantiep;
                    final String da_dung = listQ[ViTriCauHoi].QT.toUpperCase().toString();
                    dapantiep = Random_n(tra_so(da_dung));

                    for (int i=0; i<array_button.length; i++) {
                        if(i+1 != tra_so(da_dung))
                            if(i+1 != dapantiep) {
                                array_button[i].setText("");
                                array_button[i].setEnabled(false);
                            }
                    }

                    if(tra_so(da_dung) == 1) {
                        if (dapantiep == 2) {
                            mPlay = getAudioClip(R.raw.c_va_b_la__sai);
                        } else if (dapantiep == 3) {
                            mPlay = getAudioClip(R.raw.b_va_d_la__sai);
                        } else {
                            mPlay = getAudioClip(R.raw.c_va_b_la__sai);
                        }
                    } else if(tra_so(da_dung) == 2) {
                        if (dapantiep == 1) {
                            mPlay = getAudioClip(R.raw.c_va_d_la__sai);
                        } else if (dapantiep == 3) {
                            mPlay = getAudioClip(R.raw.a_va_d_la__sai);
                        } else
                            mPlay = getAudioClip(R.raw.a_va_b_la__sai);
                    } else {
                        if (tra_so(da_dung) == 3) {
                            if (dapantiep == 1) {
                                mPlay = getAudioClip(R.raw.b_va_d_la__sai);
                            } else if (dapantiep == 2) {
                                mPlay = getAudioClip(R.raw.a_va_d_la__sai);
                            } else
                                mPlay = getAudioClip(R.raw.a_va_c_la__sai);
                        } else {
                            if (dapantiep == 1) {
                                mPlay = getAudioClip(R.raw.c_va_b_la__sai);
                            } else if (dapantiep == 2) {
                                mPlay = getAudioClip(R.raw.a_va_c_la__sai);
                            } else
                                mPlay = getAudioClip(R.raw.a_va_b_la__sai);

                        }
                    }

                    mPlay.play();
                    final  Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(trangThai == false || mPlay.mPlaying == false) {
                                t.cancel();;
                                mPlay.stop();
                            }
                        }
                    },0, 50);
                }
            });
        } catch (Exception e) {
            Log.d("loi", e.getMessage());
        }

        try {
            btn_ykKhanG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(trangThai == true) {
                        mPlay = getAudioClip(R.raw.khangia);
                        mPlay.play();
                        btn_ykKhanG.setVisibility(View.INVISIBLE);
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                t.cancel();
                                Handler_showKhangia.sendEmptyMessage(0);
                            }
                        },0, 1000);
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    private int Random_n(int n) {
        Random r = new Random();
        int a = r.nextInt(4);
        while ((a == n) || (a == 0))
            a = r.nextInt(4);
        return a;
    }

    private int tra_so(String s) {
        int a = 0;
        if (s.equals("A"))
            a = 1;
        if (s.equals("B"))
            a = 2;
        if (s.equals("C"))
            a = 3;
        if (s.equals("D"))
            a = 4;
        return a;
    }

    private void saveData() {
        DataSouce database = new DataSouce(this);
        TrieuPhu user1 = new TrieuPhu();
        user1.Q = "Điền vào chỗ trống:...đến nơi làm đến chốn";
        user1.A = "Ăn";
        user1.B = "Chơi";
        user1.C = "Làm";
        user1.D = "Ngủ";
        user1.QT = "A";
        database.insertQuesTions(user1);
        TrieuPhu user2 = new TrieuPhu();
        user2.Q = "Uống rượi gây hại nhất cho bộ phận nào trong cơ thể chúng ta?";
        user2.A = "Gan";
        user2.B = "Phổi";
        user2.C = "Tim";
        user2.D = "Thần Kinh";
        user2.QT = "A";
        database.insertQuesTions(user2);
        TrieuPhu user3 = new TrieuPhu();
        user3.Q = "Love co nghia la gi";
        user3.A = "chet";
        user3.B = "yeu";
        user3.C = "ghet";
        user3.D = "thuong";
        user3.QT = "B";
        database.insertQuesTions(user3);
        TrieuPhu user4 = new TrieuPhu();
        user4.Q = "113 là số gọi khẩn cấp đến bộ phận nào?";
        user4.A = "Gọi tổng đài";
        user4.B = "Gọi cứu hỏa";
        user4.C = "Gọi cứ thương";
        user4.D = "Gọi công an";
        user4.QT = "D";
        database.insertQuesTions(user4);
        TrieuPhu user5 = new TrieuPhu();
        user5.Q = "March trong tiếng anh có nghĩa là tháng mấy trong năm";
        user5.A = "Tháng 3";
        user5.B = "Tháng 6";
        user5.C = "Tháng 5";
        user5.D = "Tháng 12";
        user5.QT = "A";
        database.insertQuesTions(user5);
        TrieuPhu user6 = new TrieuPhu();
        user6.Q = "Xe máy (còn gọi là mô-tô hay mô tô hai bánh, phiên âm từ tiếng Anh motorcycle) phương tiện mà hầu như gia đình Việt nam nào cũng có, bạn có biết loại phương tiện này do nước nào phát minh ra?";
        user6.A = "Anh";
        user6.B = "Pháp";
        user6.C = "Nhật";
        user6.D = "Đức";
        user6.QT = "D";
        database.insertQuesTions(user6);
        TrieuPhu user7 = new TrieuPhu();
        user7.Q = "Bộ phim hoạt hình nổi tiếng Shrek của hãng nào sản xuất?";
        user7.A = "Walt Disney";
        user7.B = "Pixar";
        user7.C = "DreamWorks Animation";
        user7.D = "Madhouse";
        user7.QT = "C";
        database.insertQuesTions(user7);
        TrieuPhu user8 = new TrieuPhu();
        user8.Q = "Ai được mệnh danh là: Tam Nguyên Yên Đổ?";
        user8.A = "Nguyễn Khuyến";
        user8.B = "Nguyễn Bỉnh Khiêm";
        user8.C = "Nguyễn Du";
        user8.D = "Nguyễn Trãi";
        user8.QT = "A";
        database.insertQuesTions(user8);
        TrieuPhu user9 = new TrieuPhu();
        user3.Q = "Bao nhiêu năm thì nước ta tiến hành tổng điều tra dân số 1 lần?";
        user3.A = "3 năm";
        user3.B = "5 năm";
        user3.C = "10 năm";
        user3.D = "15 năm";
        user3.QT = "C";
        database.insertQuesTions(user9);
        TrieuPhu user10 = new TrieuPhu();
        user10.Q = "Trong lịch sử câu lạc bộ Manchester United cầu thủ ghi nhiều bàn thắng nhất là ai?";
        user10.A = "Bobby Charlton";
        user10.B = "George Best";
        user10.C = "Roy Keane";
        user10.D = "Gary Neville";
        user10.QT = "A";
        database.insertQuesTions(user10);
        TrieuPhu user11 = new TrieuPhu();
        user11.Q = "Bạn có biết Tào Tuyết Cần đã dành sinh lực và tâm huyết bao nhiêu năm cuối đời để viết kiệt tác văn học Hồng Lâu Mộng?";
        user11.A = "5";
        user11.B = "10";
        user11.C = "15";
        user11.D = "20";
        user11.QT = "B";
        database.insertQuesTions(user11);
        TrieuPhu user12 = new TrieuPhu();
        user12.Q = "Love co nghia la gi";
        user12.A = "chet";
        user12.B = "yeu";
        user12.C = "ghet";
        user12.D = "thuong";
        user12.QT = "B";
        database.insertQuesTions(user12);
        TrieuPhu user13 = new TrieuPhu();
        user13.Q = "Bức tranh: Thiếu nữ bên hoa Huệ của tác giả nào?";
        user13.A = "Trần Văn Cẩn";
        user13.B = "Tô Ngọc Vân";
        user13.C = "Bùi Xuân Phái";
        user13.D = "Nguyễn Sáng";
        user13.QT = "B";
        database.insertQuesTions(user13);
        TrieuPhu user14 = new TrieuPhu();
        user14.Q = " Làng Yên Phụ ở Hà Nội nổi tiếng với nghề gì?";
        user14.A = "Kim hoàn";
        user14.B = "Làm cốm";
        user14.C = "Làm hương";
        user14.D = "Đúc đồng";
        user14.QT = "C";
        database.insertQuesTions(user14);
        TrieuPhu user15 = new TrieuPhu();
        user15.Q = "Him Lam là cứ điểm tiêu diệt thứ mấy trong chiến dịch Điện Biên Phủ?";
        user15.A = " Đầu tiên";
        user15.B = "Thứ 2";
        user15.C = "Thứ 3";

        user15.D = "Thứ 4";
        user15.QT = "A";
        database.insertQuesTions(user15);
    }

    private void LoadData() {
        DataSouce database = new DataSouce(this);
        listQ = database.getAllQu();
        listQD = database.getAllQuDe();
        listQK = database.getAllQuKh();
        lengListQ = listQ.length;
        if (listQ == null) {
            return;
        }
    }

    private void ViewData() {
        r = new Random();
        ViTriCauHoi = r.nextInt(listQ.length - vitri);
        player_vitri(vitri);

        Log.d("view1", listQ[ViTriCauHoi].A.toString());
        txtCauHoi.setText("Câu hỏi số:" + Integer.toString(vitri));
        try {
            Question.setText("	Câu hỏi số " + Integer.toString(vitri) + ": "
                    + listQ[ViTriCauHoi].Q.toString());
            A.setText("A: " + listQ[ViTriCauHoi].A.toString());
            B.setText("B: " + listQ[ViTriCauHoi].B.toString());
            C.setText("C: " + listQ[ViTriCauHoi].C.toString());
            D.setText("D: " + listQ[ViTriCauHoi].D.toString());
            vitri = vitri + 1;
        } catch (Exception e) {
            Log.d("err", e.getMessage());
        }
    }

    private void player_vitri(int vitri) {
        if (vitri == 1) {
            playAu = getAudioClip(R.raw.mot);
            playAu.play();
            return;
        }
        if (vitri == 2) {
            playAu = getAudioClip(R.raw.hai);
            playAu.play();
            return;
        }
        if (vitri == 3) {
            playAu = getAudioClip(R.raw.ba);
            playAu.play();
            return;
        }
        if (vitri == 4) {
            playAu = getAudioClip(R.raw.bon);
            playAu.play();
            return;
        }

        if (vitri == 5) {
            playAu = getAudioClip(R.raw.nam);
            playAu.play();
            return;
        }
        if (vitri == 6) {
            playAu = getAudioClip(R.raw.sau);
            playAu.play();
            return;
        }
        if (vitri == 7) {
            playAu = getAudioClip(R.raw.bay);
            playAu.play();
            return;
        }
        if (vitri == 8) {
            playAu = getAudioClip(R.raw.tam);
            playAu.play();
            return;
        }
        if (vitri == 9) {
            playAu = getAudioClip(R.raw.chin);
            playAu.play();
            return;
        }
        if (vitri == 10) {
            playAu = getAudioClip(R.raw.muoi);
            playAu.play();
            return;
        }
        if (vitri == 11) {
            playAu = getAudioClip(R.raw.muoi1);
            playAu.play();
            return;
        }
        if (vitri == 12) {
            playAu = getAudioClip(R.raw.muoi2);
            playAu.play();
            return;
        }
        if (vitri == 13) {
            playAu = getAudioClip(R.raw.muoi3);
            playAu.play();
            return;
        }
        if (vitri == 14) {
            playAu = getAudioClip(R.raw.muoi4);
            playAu.play();
            return;
        }
        if (vitri == 15) {
            playAu = getAudioClip(R.raw.muoi5);
            playAu.play();
            return;
        }
    }

    protected AudioClip getAudioClip(int id) {
        return new AudioClip(Player.this, id);
    }
}
