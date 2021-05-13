/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Materia;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.service.MateriaManager;
import com.howtodoinjava.service.UsuarioManager;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.test.context.junit4.*;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import com.howtodoinjava.forms.Registro;
import com.howtodoinjava.service.ResuelveManager;
import org.apache.commons.codec.digest.DigestUtils;
import static org.mockito.Mockito.mock;
import org.springframework.http.MediaType;



/**
 *
 * @author jt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RegistroControllerTest {
    
    public RegistroControllerTest() {
    }
    @InjectMocks
    RegistroController registroController;

    @Mock
    private MateriaManager materiaManager;
    
    @Mock
    private UsuarioManager usuarioManager;
    
    @Mock
    private ResuelveManager resuelveManager;
    
    @Mock
    View mockView;
    
    MockMvc mockMvc;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registroController).setSingleView(mockView).build();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createUsuarioProfile method, of class RegistroController.
     */
    @Test
    public void testCreateUsuarioProfile() throws Exception {
        List<Materia> list = materiaManager.getAllMaterias();
        ModelMap modelMap = new ModelMap();
        String viewName = registroController.createUsuarioProfile(modelMap);
        //Check if string value of return is equal to correct
        assertEquals("registro",viewName);
        //Try access to this route and check de ModelMaps attributes 
        mockMvc.perform(get("/registro").param("new","true"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("valueSubmit", equalTo("Register")))
                .andExpect(model().attribute("isRegistro", equalTo("1")))
                .andExpect(model().attribute("materias", equalTo(list)))
                .andExpect(model().attribute("registro", equalTo(new Registro())));
    }

    /**
     * Test of addUsuarioFromForm method, of class RegistroController.
     */
    @Test
    public void testAddUsuarioFromForm() throws Exception {
        
        /* Test de contrasena no coincide */
        
        Registro registro = new Registro("Pedrito","Perez","pedritoperez@gmail.com","pedritoperez3",1,"hola123","123");
        ModelMap modelMap = new ModelMap();
        BindingResult bindingResult = mock(BindingResult.class);
        String viewName = registroController.addUsuarioFromForm(registro,bindingResult,modelMap);
        //Check if string value of return is equal to correct
        assertEquals("registro",viewName);
        //Try access to this route and check de ModelMaps attributes 
        List<Materia> list = materiaManager.getAllMaterias();
        mockMvc.perform(post("/registro")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", registro.getNombre())
                .param("apellido", registro.getApellido())
                .param("correo", registro.getCorreo())
                .param("login", registro.getLogin())
                .param("password", registro.getPassword())
                .param("passwordConf", registro.getPasswordConf()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("valueSubmit", equalTo("Registrarse")))
                .andExpect(model().attribute("isRegistro", equalTo("1")))
                .andExpect((model().hasErrors()))
                .andExpect(model().attributeHasFieldErrorCode("registro", "passwordConf", "error.registro"));
        
        /* Test de creacion correcto */
        
        Registro registro2 = new Registro("Pedrito","Perez","pedritoperez@gmail.com","pedritoperez",1,"hola123","hola123");
        ModelMap modelMap2 = new ModelMap();
        BindingResult bindingResult2 = mock(BindingResult.class);
        String viewName2 = registroController.addUsuarioFromForm(registro2,bindingResult2,modelMap2);
        //Check if string value of return is equal to correct
        assertEquals("registrado",viewName2);
        //Try access to this route and check de ModelMaps attributes 
        Materia materia = materiaManager.getMateria(registro2.getMateriaid());
        String randomchars = "hdfGLd6J4$&(3nd^{bHGF@fs";
        String pass = DigestUtils.sha512Hex(registro.getPassword()+randomchars);
        Usuario user = new Usuario(registro2.getLogin(), registro2.getNombre(), 
                                           registro2.getApellido(), registro2.getCorreo(), 
                                           pass, materia, false);
        mockMvc.perform(post("/registro")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", registro2.getNombre())
                .param("apellido", registro2.getApellido())
                .param("correo", registro2.getCorreo())
                .param("login", registro2.getLogin())
                .param("password", registro2.getPassword())
                .param("passwordConf", registro2.getPasswordConf()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("usuario", equalTo(null)));
    }
}
