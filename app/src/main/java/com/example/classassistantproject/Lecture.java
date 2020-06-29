package com.example.classassistantproject;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class Lecture {
    //강의를 등록했을때 강의시간이 겹치는 것을 확인

    private String mon[] = new String[10];
    private String tue[] = new String[10];
    private String wed[] = new String[10];
    private String thu[] = new String[10];
    private String fri[] = new String[10];

    public Lecture(){
        for(int i=0; i<10; i++){
            mon[i] = "";
            tue[i] = "";
            wed[i] = "";
            thu[i] = "";
            fri[i] = "";
        }
    }
    public void addlecture(String lectureChoice){
        //스케줄정보를담는 텍스트가 있을때 강의정보가 들어가는 배열에 넣기
        //수:[1][2] 금:[1][2] 이렇게 데이터가 있다고하면 lectureChoice를 통해  이 함수에 들어와서 파싱됨
        int tmp;
        if((tmp = lectureChoice.indexOf("월")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //이함수로 들어온 문자열의 길이와 그 문자열의 현재위치가 :가 아닐때까지 반복
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    mon[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = "수업";   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("화")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    tue[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = "수업";   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("수")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    wed[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = "수업";   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("목")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    thu[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = "수업";   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("금")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    fri[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = "수업";   //넣을 데이터의 교시가 들어감.
                }
            }
        }
    }   //addlecture()


    public void addlecture(String lectureChoice, String courseTitle, String courseProfessor, String courseRoom){
        String professor;
        if(courseProfessor.equals("")) {
            professor = "";
        }else{
            professor="("+courseProfessor+")";
        }
        //스케줄정보를담는 텍스트가 있을때 강의정보가 들어가는 배열에 넣기
        //수:[1][2] 금:[1][2] 이렇게 데이터가 있다고하면 lectureChoice를 통해  이 함수에 들어와서 파싱됨
        int tmp;
        if((tmp = lectureChoice.indexOf("월")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //이함수로 들어온 문자열의 길이와 그 문자열의 현재위치가 :가 아닐때까지 반복
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    mon[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = courseTitle + "\n"  + professor + "\n" +courseRoom;   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("화")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    tue[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = courseTitle + "\n"  + professor + "\n" +courseRoom;   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("수")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    wed[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = courseTitle + "\n"  + professor + "\n" +courseRoom;   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("목")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    thu[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = courseTitle + "\n"  + professor + "\n" +courseRoom;   //넣을 데이터의 교시가 들어감.
                }
            }
        }
        if((tmp = lectureChoice.indexOf("금")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    fri[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))] = courseTitle + "\n"  + professor + "\n" +courseRoom;   //넣을 데이터의 교시가 들어감.
                }
            }
        }
    }   //addlecture()

    public void setting(TextView[] mon, TextView[] tue, TextView[] wed, TextView[] thu, TextView[] fri, Context context){
        for(int i=0; i<10; i++){
            if(!this.mon[i].equals("")){
                //현재시간에 미미 강의가 들어있다면
                mon[i].setText(this.mon[i]);
                mon[i].setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }

            if(!this.tue[i].equals("")){
                tue[i].setText(this.tue[i]);
                tue[i].setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }

            if(!this.wed[i].equals("")){
                wed[i].setText(this.wed[i]);
                wed[i].setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }

            if(!this.thu[i].equals("")){
                thu[i].setText(this.thu[i]);
                thu[i].setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }

            if(!this.fri[i].equals("")){
                fri[i].setText(this.fri[i]);
                fri[i].setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }
        }
    }
    public boolean validate(String lectureChoice){
        //새롭게 수강신청하려는게 기존의 신청한것과 중복되는지
        if(lectureChoice.equals("")){    //기존에 신청한게 없을때
            return true;
        }
        int tmp;
        if((tmp = lectureChoice.indexOf("월")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    if(!mon[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))].equals("")){
                        //현재값이 공백이 아니라면(해당 시간에 데이터가 들어있다면)
                        return false;
                    }
                }
            }
        }
        if((tmp = lectureChoice.indexOf("화")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    if(!tue[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))].equals("")){
                        //현재값이 공백이 아니라면(해당 시간에 데이터가 들어있다면)
                        return false;
                    }
                }
            }
        }
        if((tmp = lectureChoice.indexOf("수")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    if(!wed[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))].equals("")){
                        //현재값이 공백이 아니라면(해당 시간에 데이터가 들어있다면)
                        return false;
                    }
                }
            }
        }
        if((tmp = lectureChoice.indexOf("목")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    if(!thu[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))].equals("")){
                        //현재값이 공백이 아니라면(해당 시간에 데이터가 들어있다면)
                        return false;
                    }
                }
            }
        }
        if((tmp = lectureChoice.indexOf("금")) > -1){
            //tmp에 월이라는 단어가 있으면 그 위치를 반환하여 tmp에 들어가기
            tmp+=2;
            int frontPoint = tmp;
            int backPoint = tmp;
            for(int i =tmp; i < lectureChoice.length() && lectureChoice.charAt(i) != ':'; i++){
                //숫자데이터 파싱
                if(lectureChoice.charAt(i) == '['){
                    frontPoint = i;
                }
                if(lectureChoice.charAt(i) == ']'){
                    backPoint = i;
                    if(!fri[Integer.parseInt(lectureChoice.substring(frontPoint +1, backPoint))].equals("")){
                        //현재값이 공백이 아니라면(해당 시간에 데이터가 들어있다면)
                        return false;
                    }
                }
            }
        }
        return true;
    }   //validate()
}