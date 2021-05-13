/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.ModelMap;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.test.context.junit4.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.equalTo;

import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.service.UsuarioManager;
import javax.servlet.http.HttpSession;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.http.MediaType;


/**
 *
 * @author jt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class IndexControllerTest {
    
    @InjectMocks
    IndexController indexController;
       
    @Mock
    private UsuarioManager usuarioManager;
    
    @Mock 
    private HttpSession session;
    
    @Mock
    View mockView;
    
    MockMvc mockMvc;
    
    public IndexControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setSingleView(mockView).build();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createLoginForm method, of class IndexController.
     */
    @Test
    public void testCreateLoginForm() throws Exception{
        ModelMap modelMap = new ModelMap();
        String viewName = indexController.createLoginForm(modelMap);
        //Check if string value of return is equal to correct
        assertEquals("index",viewName);
        //Try access to this route and check de ModelMaps attributes 
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("usuariolog", instanceOf(Usuario.class)))
                .andExpect(model().attribute("mensaje", equalTo("")));
    }

    /**
     * Test of validarUsuarioFromForm method, of class IndexController.
     */
    @Test
    public void testValidarUsuarioFromForm() throws Exception{
        
        /* Test de usuario que no existe */
        
        ModelMap modelMap = new ModelMap();
        Usuario usuariolog = new Usuario();
        usuariolog.setLogin("pedroperez");
        usuariolog.setPassword("Hola");
        String viewName = indexController.validarUsuarioFromForm(usuariolog,modelMap);
        //Check if string value of return is equal to correct
        assertEquals("index",viewName);
        //Try access to this route and check de ModelMaps attributes 
        mockMvc.perform(post("/index")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", usuariolog.getLogin())
                .param("password", usuariolog.getPassword()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("usuariolog", instanceOf(Usuario.class)))
                .andExpect(model().attribute("mensaje", equalTo("Login o password errados")));
    }

    /**
     * Test of showHelp method, of class IndexController.
     */
    @Test
    public void testShowHelp() throws Exception{
        ModelMap modelMap = new ModelMap();
        String viewName = indexController.showHelp(modelMap);
        //Check if string value of return is equal to correct
        assertEquals("help",viewName);
        //Try access to this route and check de ModelMaps attributes 
        mockMvc.perform(get("/index/help"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sesion", equalTo("logout")));
    }
    
}
