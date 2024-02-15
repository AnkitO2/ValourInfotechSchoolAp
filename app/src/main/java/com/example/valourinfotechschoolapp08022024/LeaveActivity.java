package com.example.valourinfotechschoolapp08022024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.valourinfotechschoolapp08022024.databinding.ActivityLeaveBinding;
import com.example.valourinfotechschoolapp08022024.responses.StudentLeaveRequest;
import com.example.valourinfotechschoolapp08022024.responses.StudentLeaveResponse;
import com.example.valourinfotechschoolapp08022024.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveActivity extends AppCompatActivity {
private ActivityLeaveBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLeaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.studentId.setText(""+getIntent().getStringExtra("UserId"));

    binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Integer.parseInt(binding.EditLeaveDays.getText().toString())<=2){
                StudentLeaveDetails();
            }
            else {
                Toast.makeText( LeaveActivity.this, "Leave Not Allowed For more Than Two Days", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
    private void StudentLeaveDetails(){
        StudentLeaveRequest studentLeaveRequest =new StudentLeaveRequest();
        studentLeaveRequest.setStudentId(binding.studentId.getText().toString());
        studentLeaveRequest.setLeaveReason(binding.EditLeaveReason.getText().toString());
        studentLeaveRequest.setLeaveDays(binding.EditLeaveDays.getText().toString());
        RetrofitClient.getClient().StudentLeaveCall(studentLeaveRequest).enqueue(new Callback<StudentLeaveResponse>() {
            @Override
            public void onResponse(Call<StudentLeaveResponse> call, Response<StudentLeaveResponse> response) {
                if (response.isSuccessful()){

                    Log.d("Response", "Body: " + response.body().toString());
                    binding.studentIdId.setText(response.body().getStudentLeaveDetails().getStudentId());
                    binding.LeaveDays.setText(response.body().getStudentLeaveDetails().getLeaveDays());
                    binding.LeaveReason.setText(response.body().getStudentLeaveDetails().getLeaveReason());
                    binding.leaveStatus.setText(response.body().getApplicationMessage());
                } else {
                    Toast.makeText(LeaveActivity.this, "response is not successfully", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StudentLeaveResponse> call, Throwable t) {
            Toast.makeText(LeaveActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
