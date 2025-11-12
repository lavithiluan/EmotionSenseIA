package com.fiap.gs.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class GenerativeAiService {

    private final ChatClient chatClient;

    public GenerativeAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Async
    public void gerarDica(String humor) {
        System.out.println("[IA] Conectando ao Gemini para gerar dica...");
        String prompt = String.format(
                """
                Você é um assistente de bem-estar corporativo, solidário e positivo.
                Um funcionário acabou de registrar seu humor como: %s.

                Gere uma dica de bem-estar muito curta (máximo 2-3 frases).
                
                **REGRAS IMPORTANTES:**
                1. Seja criativo e evite dar a mesma resposta sempre.
                2. Se o humor for "TRISTE" ou "ANSIOSO", sugira uma ação de conexão humana.
                3. Para outros humores (como "NORMAL" ou "FELIZ"), sugira uma ação prática 
                   e rápida na mesa de trabalho (ex: alongar, beber água, organizar a mesa por 1 minuto).
                """,
                humor
        );
        // ========================================================================

        try {
            String respostaDaIA = this.chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            System.out.println("[IA - DICA GERADA]: " + respostaDaIA);

        } catch (Exception e) {
            System.err.println("Erro ao chamar a API do Gemini: " + e.getMessage());
        }
    }
}