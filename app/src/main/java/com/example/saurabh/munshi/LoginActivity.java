package com.example.saurabh.munshi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Models.AadharModel;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "PhoneLogin";
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    TextView t1, t2;
    ImageView i1;
    EditText e1, e2;
    Button b1, b2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private ProgressDialog progressDialog;

    public Button Log_in;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = (EditText) findViewById(R.id.Phonenoedittext);
        b1 = (Button) findViewById(R.id.PhoneVerify);
        t1 = (TextView)findViewById(R.id.textView2Phone);
        i1 = (ImageView)findViewById(R.id.imageView2Phone);
        e2 = (EditText) findViewById(R.id.OTPeditText);
        b2 = (Button)findViewById(R.id.OTPVERIFY);
        t2 = (TextView)findViewById(R.id.textViewVerified);
        progressDialog  = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(mAuth.getCurrentUser()!=null){
                    Intent loginIntent=new Intent(LoginActivity.this,after_login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);

                }
            }
        };
        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Aadhar");
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                Toast.makeText(LoginActivity.this,"Verification Complete",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                progressDialog.setMessage("Login in....");
                progressDialog.show();
                signInWithPhoneAuthCredential(credential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(LoginActivity.this,"Verification Failed",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(LoginActivity.this,"InValid Phone Number",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {

                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(LoginActivity.this,"Verification code has been send on your number",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                e1.setVisibility(View.GONE);
                b1.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                i1.setVisibility(View.GONE);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                // ...
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Fecthing Mobile Number");
                progressDialog.show();
                String aadhar=e1.getText().toString();
                //((Munshi2)this.getApplication()).setSomeVariable(aadhar);
                DatabaseReference aadhar_number = mDatabaseUsers.child(aadhar).child("Mobile_number");
                aadhar_number.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String mobile_no=dataSnapshot.getValue().toString();
                        mobile_number(mobile_no);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Toast.makeText(LoginActivity.this,databaseError.toString(),Toast.LENGTH_LONG).show();

                    }
                });


                progressDialog.dismiss();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Verifying");
                progressDialog.show();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, e2.getText().toString());
                // [END verify_with_code]
                signInWithPhoneAuthCredential(credential);


            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "signInWithCredential:success");
                            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                        Toast.makeText(LoginActivity.this,"Verification Done",Toast.LENGTH_SHORT).show();

                                        progressDialog.dismiss();
                                        Intent loginIntent=new Intent(LoginActivity.this,after_login.class);

                                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(loginIntent);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            //startActivity(new Intent(login.this,MainActivity.class));
                            // Toast.makeText(login.this,"Verification Done",Toast.LENGTH_SHORT).show();
                            // ...
                        } else {
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(LoginActivity.this,"Invalid Verification",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    void mobile_number(String Mobile_no) {
        progressDialog.dismiss();
        progressDialog.setMessage("Sending OTP to "+Mobile_no);
        progressDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Mobile_no,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                LoginActivity.this,
                mCallbacks);
        /*DatabaseReference aadhar_number = mDatabaseUsers.child(e1.getText().toString()).child("Mobile_number");
        String s=e1.getText().toString();
        final AadharModel aa=new AadharModel();
        aadhar_number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mobile_no=dataSnapshot.getValue().toString();


        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(LoginActivity.this,databaseError.toString(),Toast.LENGTH_LONG).show();

            }
        });*/

    }

}



