package com.example.garbagesorting.ui.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.garbagesorting.R;
import com.example.garbagesorting.util.MySqliteOpenHelper;
import com.example.garbagesorting.util.SPUtils;


/**
 * 重置密码
 */
public class PasswordActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private Activity activity;
    private EditText etNewPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity =this;
        setContentView(R.layout.activity_password);
        helper = new MySqliteOpenHelper(activity);
        etNewPassword = findViewById(R.id.et_new_password);
    }

    //保存信息
    public void save(View v){
        SQLiteDatabase db = helper.getWritableDatabase();
        String newPassword = etNewPassword.getText().toString();
        if ("".equals(newPassword)){//密码为空
            Toast.makeText(activity,"The new password is empty", Toast.LENGTH_LONG).show();
            return;
        }
        Integer userId = (Integer) SPUtils.get(PasswordActivity.this,SPUtils.USER_ID,0);
        db.execSQL("update user set password = ? where id = ?", new Object[]{userId});
        Toast.makeText(PasswordActivity.this,"update successfully",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void back(View view){
        finish();
    }
}
