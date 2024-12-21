package com.example.software_experiment_7.Text;

import com.example.software_experiment_7.DAO.Model.Organization;
import com.example.software_experiment_7.DAO.Model.User;
import com.example.software_experiment_7.Service.TreeService;
import com.sun.source.tree.Tree;
import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class ServiceTest {
    TreeService treeService = new TreeService();
    public ServiceTest() throws SQLException, ClassNotFoundException {
    }

    @Test
    public void login() {
        boolean isSuccess = treeService.login("202121210212", "e10adc3949ba59abbe56e057f20f883e");
        assertTrue(isSuccess);
        System.out.println();
    }

    @Test
    public void getUser(){
        User user = treeService.getUserByID("172");
        assertEquals("172",user.getfID());
        assertEquals("|01010305||01020205|",user.getfOrgIDs());
        assertEquals("9dfa7535-795b-43ed-8a5d-fcd894013907",user.getfUserGUID());
        assertEquals("202225220608",user.getfName());
        assertEquals("e10adc3949ba59abbe56e057f20f883e",user.getfPassword());
        assertEquals("22软工R6",user.getfRemark());
        System.out.println();
    }

    @Test
    public void getOrganizationByID(){
        Organization org = treeService.getOrganizationByID("01");
        assertEquals("01",org.getfID());
        assertEquals("总公司",org.getfName());
    }

    @Test
    public void addOrganization(){
        Organization org = new Organization("01040304","销售", "|3010||3677||6207||6491|","010403","销售","abcd-efgh-ijkl-mnop");
        treeService.addOrganization(org);
        Organization organization = treeService.getOrganizationByID("01040304");
        assertEquals("01040304",organization.getfID());
        assertEquals("销售",organization.getfName());
        assertEquals("|3010||3677||6207||6491|",organization.getfPermission());
    }

    @Test
    public void deleteOrganization(){
        Organization org = new Organization("01040304","销售", "|3010||3677||6207||6491|","010403","销售","abcd-efgh-ijkl-mnop");
        treeService.deleteOrganization(org);
        assertNull(treeService.getOrganizationByID("01040304"));
    }
}
