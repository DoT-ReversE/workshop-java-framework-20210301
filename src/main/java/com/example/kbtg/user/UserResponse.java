package com.example.kbtg.user;

public class UserResponse {
    private Integer id;
    private String name;
    private Integer age;

    public UserResponse() {
    }

    public UserResponse(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        boolean isSameClass = obj != null && getClass() == obj.getClass();
        if (isSameClass) {
            UserResponse compareObj = (UserResponse) obj;
            isEquals =
                    this.id.equals(compareObj.getId())
                    && this.name.equals(compareObj.getName())
                    && this.age.equals(compareObj.getAge());
        }
        return isEquals;
    }
}