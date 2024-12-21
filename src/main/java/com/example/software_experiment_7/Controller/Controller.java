package com.example.software_experiment_7.Controller;

import com.example.software_experiment_7.DAO.Model.Organization;
import com.example.software_experiment_7.DAO.Model.User;
import com.example.software_experiment_7.DTO.LoginRequest;
import com.example.software_experiment_7.DTO.LoginResponse;
import com.example.software_experiment_7.Service.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/*
实现对用户操作的Controller类
实现前端对后端的URL访问，与WebApi接口关联
 */
@RestController
public class Controller {

    private final TreeService treeService = new TreeService();

    public Controller() throws SQLException, ClassNotFoundException {
    }

    // 用户登录接口
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean isSuccess = treeService.login(loginRequest.getUsername(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse();

        if (isSuccess) {
            response.setSuccess(true);
            response.setMessage("登录成功");
            return ResponseEntity.ok(response);
        } else {
            response.setSuccess(false);
            response.setMessage("用户名或密码错误");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 添加用户
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) throws SQLException {
        treeService.addUser(user);
        return ResponseEntity.ok("用户添加成功");
    }

    //根据用户ID查找,postman中直接在URL内输入localhost:8080/user/ + 用户ID
    @GetMapping("/user/{fID}")
    public ResponseEntity<?> getUser(@PathVariable String fID) {
        User user = treeService.getUserByID(fID);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有找到该用户");
        }
    }

    // 获取所有用户
    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = treeService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 更新用户
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        treeService.updateUser(user);
        return ResponseEntity.ok("用户属性更新成功");
    }

    // 删除用户
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        treeService.deleteUser(user);
        return ResponseEntity.ok("用户删除成功");
    }

    //添加机构
    @PostMapping("/addOrganization")
    public ResponseEntity<?> addOrg(@RequestBody Organization organization) throws SQLException {
        treeService.addOrganization(organization);
        return ResponseEntity.ok("机构添加成功");
    }

    //更新机构属性
    @PutMapping("/updateOrganization")
    public ResponseEntity<?> updateOrg(@RequestBody Organization organization) {
        treeService.UpdateOrganization(organization);
        return ResponseEntity.ok("机构属性更新成功");
    }

    //删除机构
    @DeleteMapping("/deleteOrganization")
    public ResponseEntity<?> deleteOrg(@RequestBody Organization organization) {
        treeService.deleteOrganization(organization);
        return ResponseEntity.ok("机构删除成功");
    }

    //查找全部机构
    @GetMapping("/allOrganization")
    public ResponseEntity<?> getAllOrganization() {
        List<Organization> Organizations = treeService.getAllOrganization();
        return ResponseEntity.ok(Organizations);
    }

    //根据机构fID查找机构
    @GetMapping("/Organization/{fID}")
    public ResponseEntity<?> getOrganization(@PathVariable String fID) {
        Organization organization = treeService.getOrganizationByID(fID);
        if (organization != null) {
            return ResponseEntity.ok(organization);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有找到该机构");
        }
    }
}

