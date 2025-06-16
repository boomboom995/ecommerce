package com.customer.ecommerce.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class Customer {
    private Long id;

    @NotBlank(message = "客户姓名不能为空")
    @Size(min = 2, max = 50, message = "姓名长度必须在2到50之间")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private String address;

    private Integer age;

    private BigDecimal returnRate;
}