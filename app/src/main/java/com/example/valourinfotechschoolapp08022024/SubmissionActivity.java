package com.example.valourinfotechschoolapp08022024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.valourinfotechschoolapp08022024.databinding.ActivitySubmissionBinding;
import com.example.valourinfotechschoolapp08022024.requests.StudentFeesSubmissionRequest;
import com.example.valourinfotechschoolapp08022024.responses.StudentFeesSubmissionResponse;
import com.example.valourinfotechschoolapp08022024.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
private ActivitySubmissionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.studentId.setText(""+getIntent().getStringExtra("UserId"));
        FeesSubmissionDetails();
    }
    private void FeesSubmissionDetails(){
        StudentFeesSubmissionRequest studentFeesSubmissionRequest =new  StudentFeesSubmissionRequest();
        studentFeesSubmissionRequest.setStudentId(getIntent().getStringExtra("UserId"));
        studentFeesSubmissionRequest.setFinYear("2021");
        studentFeesSubmissionRequest.setStudentName("Anshu Kumar");
        studentFeesSubmissionRequest.setClassName("Montessory");
        studentFeesSubmissionRequest.setSectionName("A");
        studentFeesSubmissionRequest.setFeeTypeName("Addmission Fee");
        studentFeesSubmissionRequest.setFeeAmount("500");
        studentFeesSubmissionRequest.setLateFeePaid("50");
        studentFeesSubmissionRequest.setTotalPaid("550");
        studentFeesSubmissionRequest.setPaymentMode("Cash");
        studentFeesSubmissionRequest.setPaymentDate("20/01/2023");
        studentFeesSubmissionRequest.setPaymentHead("Cash In Hand");
        studentFeesSubmissionRequest.setPayTransId("12345");
        studentFeesSubmissionRequest.setApproveStatus("Pending");
        RetrofitClient.getClient().StudentFeesCall(studentFeesSubmissionRequest).enqueue(new Callback<StudentFeesSubmissionResponse>() {
            @Override
            public void onResponse(Call<StudentFeesSubmissionResponse> call, Response<StudentFeesSubmissionResponse> response) {
                if (response.isSuccessful()){
                    Log.d("Response", "Body: " + response.body().toString());
                     binding.textId1.setText(response.body().getFeeSubmitMessage());
                     binding.textId2.setText(response.body().getStudentFeesSubmission().getClassName());
                     binding.textId3.setText(response.body().getStudentFeesSubmission().getFeeAmount());
                     binding.textId4.setText(response.body().getStudentFeesSubmission().getFeeTypeName());
                     binding.textId5.setText(response.body().getStudentFeesSubmission().getFinYear());
                     binding.textId6.setText(response.body().getStudentFeesSubmission().getLateFeePaid());
                     binding.textId7.setText(response.body().getStudentFeesSubmission().getPayTransId());
                     binding.textId8.setText(response.body().getStudentFeesSubmission().getPaymentDate());
                     binding.textId9.setText(response.body().getStudentFeesSubmission().getPaymentHead());
                     binding.textId10.setText(response.body().getStudentFeesSubmission().getPaymentMode());
                     binding.textId11.setText(response.body().getStudentFeesSubmission().getSectionName());
                     binding.textId12.setText(response.body().getStudentFeesSubmission().getStudentName());
                     binding.textId13.setText(response.body().getStudentFeesSubmission().getTotalPaid());
                     //binding.textId12.setText(response.body().getStudentFeesSubmission().getStudentId());
                }else {
                    Toast.makeText(SubmissionActivity.this, "response is not successfully"+response.body(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StudentFeesSubmissionResponse> call, Throwable t) {
                Toast.makeText(SubmissionActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}