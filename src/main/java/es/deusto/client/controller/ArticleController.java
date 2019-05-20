package es.deusto.client.controller;

import es.deusto.client.model.ClientModel;
import es.deusto.client.view.ArticleJDialog;
import es.deusto.server.jdo.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArticleController {
    private ArticleJDialog article;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea bodyTextArea;
    private JTextField textFieldTitle;

    private ClientModel model;
    private Article art;

    ArticleController(String title) {
        initComponents();
        initListeners();
        initModel();

        art = model.getArticle(title);

        textFieldTitle.setText(art.getTitle());
        bodyTextArea.setText(art.getBody());
    }

    private void initModel() {
        model = ClientModel.getModel();
    }

    private void initComponents() {
        article = new ArticleJDialog();

        textFieldTitle = article.getTextFieldTitle();
        bodyTextArea = article.getBodyTextArea();
        buttonCancel = article.getButtonCancel();
    }

    void showArticleWindow() {
        //Show landing page
        article.setVisible(true);
    }

    private void initListeners() {
        buttonCancel.addActionListener(this::onCancel);
    }

    private void onCancel(ActionEvent actionEvent) {
        // add your code here
        article.dispose();
    }
}
