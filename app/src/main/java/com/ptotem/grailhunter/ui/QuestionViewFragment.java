package com.ptotem.grailhunter.ui;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptotem.grailhunter.R;
import com.ptotem.grailhunter.core.City;
import com.ptotem.grailhunter.core.GrailEngine;
import com.ptotem.grailhunter.core.Question;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class QuestionViewFragment extends Fragment implements View.OnClickListener
{
    private Question question;
    private GrailEngine grailEngine;
    private TextView questionTextView;
    private RadioButton[] options;
    private RelativeLayout answerBackground;
    private RelativeLayout answerLayout;
    private TextView answerInfo;
    private City city;

    public QuestionViewFragment() {
        options=new RadioButton[4];
        // Required empty public constructor
    }

    public void setCity(City city)
    {
        this.city=city;
    }

    public void setQuestion(Question question)
    {
        this.question=question;
    }

    public void setGrailEngine(GrailEngine grailEngine)
    {
        this.grailEngine=grailEngine;
    }

    public void replaceWithAnswerInfo()
    {
        answerBackground.removeAllViews();
        answerBackground.addView(answerLayout);
    }

    public void setAnswerInfoText(String text)
    {
        if(answerInfo!=null)
            answerInfo.setText(text);
    }

    public void onClick(View view)
    {
        boolean answer=false;
        int answeredByPlayer=((Integer)view.getTag()).intValue();
        if(answeredByPlayer==question.getAnswer())
            answer=true;
        else
            answer=false;
        grailEngine.answerQuestion(answer,question.getPayoff());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_question_view, container, false);
        questionTextView=(TextView)view.findViewById(R.id.question);
        answerBackground=(RelativeLayout)view.findViewById(R.id.answerBackground);
        options[0]=(RadioButton)view.findViewById(R.id.option1);
        options[0].setTag(new Integer(1));
        options[0].setOnClickListener(this);
        options[1]=(RadioButton)view.findViewById(R.id.option2);
        options[1].setTag(new Integer(2));
        options[1].setOnClickListener(this);
        options[2]=(RadioButton)view.findViewById(R.id.option3);
        options[2].setTag(new Integer(3));
        options[2].setOnClickListener(this);
        options[3]=(RadioButton)view.findViewById(R.id.option4);
        options[3].setTag(new Integer(4));
        options[3].setOnClickListener(this);
        answerLayout=(RelativeLayout)inflater.inflate(R.layout.layout_answer_info,answerBackground,false);
        ((Button)answerLayout.findViewById(R.id.leaveAnswerButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grailEngine.leaveFromAnsweringMode(city);
            }
        });
        answerInfo=(TextView)answerLayout.findViewById(R.id.answerInfo);
        if(question!=null)
            populateDataWithQuestion(question);
        return view;
    }

    private void populateDataWithQuestion(Question q)
    {
        questionTextView.setText(q.getName());
        options[0].setText(q.getOpta());
        options[1].setText(q.getOptb());
        options[2].setText(q.getOptc());
        options[3].setText(q.getOptd());
    }
}
