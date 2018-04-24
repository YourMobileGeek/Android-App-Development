package com.example.arianad.tasklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class TaskList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TaskListDB db = new TaskListDB(this);
        StringBuilder sb = new StringBuilder();
        Task task = new Task(1, "Pay Car Insurance", "", "0", "0");
        long insertId = db.insertTask(task);

        if (insertId > 0) {
            sb.append("Row inserted! Insert Id: " + insertId + "\n");
        }

        Task task2 = new Task(1, "Refill Prescription", "", "0", "0");
        long insertId2 = db.insertTask(task2);

        if (insertId2 > 0) {
            sb.append("Row inserted! Insert Id: " + insertId2 + "\n");
        }

        Task task3 = new Task(1, "Pay Car Payment Loan", "", "0", "0");
        long insertId3 = db.insertTask(task3);

        if (insertId3 > 0) {
            sb.append("Row inserted! Insert Id: " + insertId3 + "\n");
        }

        Task task4 = new Task(1, "Purchase Beyonce OTR II Tour Tickets", "", "0", "0");
        long insertId4 = db.insertTask(task4);

        if (insertId4 > 0) {
            sb.append("Row inserted! Insert Id: " + insertId4 + "\n");
        }

        Task task5 = new Task(1, "Go Food Shopping", "", "0", "0");
        long insertId5 = db.insertTask(task5);

        if (insertId5 > 0) {
            sb.append("Row inserted! Insert Id: " + insertId5 + "\n");
        }

        task.setId((int) insertId);
        task.setName("Update test");

        int updateCount = db.updateTask(task);

        if (updateCount == 1) {
            sb.append("Task updated! Update count: " + updateCount + "\n");
        }

        int deleteCount = db.deleteTask(insertId);

        if (deleteCount == 1) {
            sb.append("Task deleted! Delete count: " + deleteCount + "\n\n");
        }

        db.deleteTask(5);
        db.deleteTask(7);

        ArrayList<Task> tasks = db.getTasks("Personal");

        for (Task t : tasks) {
            sb.append(t.getId() + "|" + t.getName() + "\n");
        }

        TextView taskListTextView = (TextView)
                findViewById (R.id.taskListTextView);

        taskListTextView.setText(sb.toString());
    }
}