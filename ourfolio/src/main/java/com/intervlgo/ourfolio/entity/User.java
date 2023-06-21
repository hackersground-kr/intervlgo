package com.intervlgo.ourfolio.entity;

import com.intervlgo.ourfolio.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk")
    private Long pk;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPassword;
    private String username;
    private String region;
    private String occupation;

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUsername(username);
        userDto.setRegion(region);
        userDto.setOccupation(occupation);
        return userDto;
    }

    public void update(String username, String region, String occupation){
        if (username != null) this.username= username;
        if (region != null) this.region = region;
        if (occupation != null) this.occupation = occupation;
    }

    public void update(String Id, String userPassword) {
        if (userPassword != null) this.userPassword = userPassword;
        if (Id != null) this.userId = Id;
    }
}
