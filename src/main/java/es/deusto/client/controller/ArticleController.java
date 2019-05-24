package es.deusto.client.controller;

import es.deusto.client.logger.LoggerClient;
import es.deusto.client.model.ClientModel;
import es.deusto.client.view.ArticleJDialog;
import es.deusto.server.jdo.Article;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArticleController {
    private ArticleJDialog article;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea bodyTextArea;
    private JTextField textFieldTitle;

    private Logger LOGGER;
    private ClientModel model;
    private Article art;
    private Integer mode;
    private Boolean newArticle = false;

    ArticleController(String title) {
        LOGGER =  LoggerClient.getLogger();

        initComponents();
        initListeners();
        initModel();


        mode = model.getMode();

        if(title != null) {
            art = model.getArticle(title);
            textFieldTitle.setText(art.getTitle());
            bodyTextArea.setText(art.getBody());
        } else {
            newArticle = true;
        }

        if(mode == 2) {
            buttonOK.setVisible(true);
            textFieldTitle.setEnabled(true);
            bodyTextArea.setEnabled(true);
        }
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void initComponents() {
        article = new ArticleJDialog();

        textFieldTitle = article.getTextFieldTitle();
        bodyTextArea = article.getBodyTextArea();
        buttonCancel = article.getButtonCancel();
        buttonOK = article.getButtonOK();
    }

    void showArticleWindow() {
        //Show landing page
        article.setVisible(true);
    }

    private void initListeners() {
        buttonCancel.addActionListener(this::onCancel);
        buttonOK.addActionListener(this::onOK);
    }

    private void onCancel(ActionEvent actionEvent) {
        // add your code here
        article.dispose();
    }

    private void onOK(ActionEvent actionEvent) {
        String title = textFieldTitle.getText();
        String body = bodyTextArea.getText();

        if(!newArticle) {
            //model.editArticle(art, )
        } else {
            model.addArticle(title, body);
        }
        article.dispose();
    }
}
