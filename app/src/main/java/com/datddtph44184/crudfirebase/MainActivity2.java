package com.datddtph44184.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    TextView tvKQ;
    FirebaseFirestore database;
    Context context = this;
    String strKQ="";
    ToDo toDo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvKQ = findViewById(R.id.tvKQ);
        database = FirebaseFirestore.getInstance();
        insert();
//        update();
//        select();
//        delete();
    }
    void insert(){
        String id = UUID.randomUUID().toString();//lấy chuỗi ngẫu nhiên
        toDo = new ToDo(id , "title 11","content 11");//tạo đối tượng insert
        database.collection("TODO").document(id)
                .set(toDo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {//thành công
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "insert thành công", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "insert thất bại", Toast.LENGTH_SHORT).show();
                    }
                });//truy cập đến bảng dữ liệu

    }
    void update(){
        String id = "cf333965-2140-4953-a043-fc2f0e65556f";
        toDo = new ToDo(id , "title 11 update","content 11 update");//nội dung cần update
        database.collection("TODO").document(id)
                .update(toDo.convertToHashMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "update thành công", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void delete(){
        String id="cf333965-2140-4953-a043-fc2f0e65556f";
        database.collection("TODO").document(id)
                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    ArrayList<ToDo> select(){
        ArrayList<ToDo> list = new ArrayList<>();
        database.collection("TODO").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            strKQ ="";
                            for(QueryDocumentSnapshot doc: task.getResult()){
                                ToDo t=doc.toObject(ToDo.class);//chuyển dữ liệu đọc được sang dạng todo
                                list.add(t);
                                strKQ+= "id: " + t.getId()+"\n";
                                strKQ+= "title: " + t.getTitle()+"\n";
                                strKQ+= "content: " + t.getContent()+"\n";
                            }
                            tvKQ.setText(strKQ);
                        }
                        else{
                            Toast.makeText(context, "select thất bại", Toast.LENGTH_SHORT).show();
                    }

                    }
                });
        return list;
    }

}