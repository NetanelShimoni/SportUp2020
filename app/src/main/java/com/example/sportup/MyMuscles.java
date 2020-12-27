package com.example.sportup;

public class MyMuscles {

    private String MusclesName;
            private Integer MusclesImage;

            public MyMuscles(String MusclesName,  Integer MusclesImage) {
        this.MusclesName = MusclesName;
        this.MusclesImage = MusclesImage;
    }

    public String getMusclesName() {
        return MusclesName;
    }

    public void setMusclesName(String musclesName) {
        this.MusclesName = musclesName;
    }


    public Integer getMusclesImage() {
        return MusclesImage;
    }

    public void setMusclesImage(Integer musclesImage) {
        this.MusclesImage = musclesImage;
    }
}