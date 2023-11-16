package com.topdata.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//@RestController
//@RequestMapping("/")
@Controller
public class CadastroController {
	private final ResourceLoader resourceLoader;

    //@Autowired
    public CadastroController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
   
    
    @GetMapping("/")
    @ResponseBody
    public String exibirFormularioLogin() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/views/loginUser.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    //@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    @GetMapping("/cadastro")
    @ResponseBody
    public String exibirFormularioCadastro() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/views/cadastro.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    @GetMapping("/detalhe")
    @ResponseBody
    public String exibirDetalheusuario() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/views/detalhe.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    @GetMapping("/listagem")
    @ResponseBody
    public String exibirTelaListagem() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/views/listagem.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
}