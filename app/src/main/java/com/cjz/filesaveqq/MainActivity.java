package com.cjz.filesaveqq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cjz.filesaveqq.utils.FileSaveQQ2;

import java.util.Map;

/**
 *  author: ChenJingZhuo
 *  date: 2019/11/22
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtAccount;    //账号输入框
    private EditText mEtPassword;   //密码输入框
    private Button mBtnLogin;       //登录按钮

    String account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //通过工具类FileSaveQQ中的getUserInfo()方法获取QQ账号和密码信息
        Map<String, String> userInfo = FileSaveQQ2.getUserInfo();
        if (userInfo != null) {
            //将获取的账号显示到界面上
            mEtAccount.setText(userInfo.get("account"));
            //将获取的密码显示到界面上
            mEtPassword.setText(userInfo.get("password"));
        }
    }

    public void initView() {
        mEtAccount = findViewById(R.id.et_account);
        mEtPassword = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);
        //设置按钮的点击监听事件
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //当点击“登录”按钮时，获取界面上输入的QQ账号和密码
                account = mEtAccount.getText().toString().trim();
                //检测输入的账号和密码是否为空
                password = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                String account_p = "\\w+@\\w+(\\.w{2,3})*\\.\\w{2,3}";
                String password_p = "\\p{Alnum}{8,16}";

                if (!account.matches(account_p)) {
                    Toast.makeText(this, "账号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.matches(password_p)) {
                    Toast.makeText(this, "密码不合法", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String p2 = "\\d{8,16}";
                    String p3 = "\\p{Alpha}{8,16}";
                    if (password.matches(p2) || password.matches(p3)) {
                        Toast.makeText(this, "密码不合法", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    getUserInfo();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败，不能读取系统短信", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getUserInfo() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //保存用户信息
        boolean isSaveSuccess = FileSaveQQ2.saveUserInfo(account, password);
        if (isSaveSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
