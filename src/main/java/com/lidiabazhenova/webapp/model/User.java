package com.lidiabazhenova.webapp.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class User extends AbstractModel {

    private long userId;
    private String login;
    private String firstName;
    private String lastName;
    private String password;

    public User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.login = userBuilder.login;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.password = userBuilder.password;
    }

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return userId == user.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class UserBuilder {
        private long userId;
        private String login;
        private String firstName;
        private String lastName;
        private String password;

        public UserBuilder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    //TODO validateRequiredFields;

    public boolean validateNotBlank() {
        if ((StringUtils.isNotBlank(login))
                & (StringUtils.isNotBlank(password))
                & (StringUtils.isNotBlank(firstName))
                & (StringUtils.isNotBlank(lastName))) {
            return true;
        } else return false;
    }
}

