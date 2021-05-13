/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Predicado;
import com.howtodoinjava.entity.Simbolo;
import com.howtodoinjava.entity.Teoria;
import com.howtodoinjava.forms.AgregarSimbolo;
import com.howtodoinjava.forms.AgregarTeorema;
import com.howtodoinjava.forms.InferResponse;
import com.howtodoinjava.forms.ModificarAliasForm;
import com.howtodoinjava.forms.ModificarForm;
import com.howtodoinjava.forms.MostrarCategoriaForm;
import com.howtodoinjava.forms.Registro;
import com.howtodoinjava.forms.UsuarioGuardar;
import com.howtodoinjava.forms.teoremasSolucion;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import com.howtodoinjava.service.SimboloManager;
import com.howtodoinjava.service.TeoriaManager;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;
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
public class PerfilControllerTest {
    
    @InjectMocks
    PerfilController perfilController;
       
    @Mock
    private UsuarioManager usuarioManager;
    
    @Mock
    private SimboloManager simboloManager;
    
    @Mock
    private TeoriaManager teoriaManager;
    
    @Mock 
    private HttpSession session;
    
    @Mock
    View mockView;
    
    MockMvc mockMvc;
    
    public PerfilControllerTest() {
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
        mockMvc = MockMvcBuilders.standaloneSetup(perfilController).setSingleView(mockView).build();
        
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of TheoriesView method, of class PerfilController.
     */
    @Test
    public void testTheoriesView() throws Exception {
        
        /* Case when user doesnt exists */
        
        ModelMap modelMap = new ModelMap();
        String viewName = perfilController.TheoriesView("pedritoperez",modelMap);
        //Check if string value of return is equal to correct
        assertEquals("redirect:/index",viewName);
        //Try access to this route and check de ModelMaps attributes 
        Usuario usr = usuarioManager.getUsuario("pedritoperez");
        List<Simbolo> listaSimbolos = simboloManager.getAllSimbolo();
        List<Teoria> listaTeorias = teoriaManager.getAllTeoria();
        
//        map.addAttribute("usuario", usr);
//        map.addAttribute("mensaje","");
//        map.addAttribute("guardarMenu","");
//        map.addAttribute("listarTerminosMenu","");
//        map.addAttribute("misTeoremasMenu","");        
//        map.addAttribute("agregarTeoremaMenu","");        
//        map.addAttribute("perfilMenu","");
//        map.addAttribute("theoMenu","active");
//        map.addAttribute("students","");
//        map.addAttribute("isAdmin",usr.isAdmin()?new Integer(1):new Integer(0));
//        map.addAttribute("helpMenu","");
//        map.addAttribute("overflow","hidden");
//        map.addAttribute("anchuraDiv","1100px");
//        map.addAttribute("listaSimbolos",listaSimbolos);
//        map.addAttribute("listaTeorias",listaTeorias);
//        map.addAttribute("agregarSimbolo",new AgregarSimbolo());
//        map.addAttribute("modificarSimbolo",new AgregarSimbolo());
        mockMvc.perform(get("/pedritoperez/theo"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test of guardarTeoria method, of class PerfilController.
     */
//    @Test
//    public void testGuardarTeoria() {
//        System.out.println("guardarTeoria");
//        AgregarSimbolo agregarSimbolo = null;
//        BindingResult bindingResult = null;
//        String username = "";
//        ModelMap map = null;
//        PerfilController instance = new PerfilController();
//        String expResult = "";
//        String result = instance.guardarTeoria(agregarSimbolo, bindingResult, username, map);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
