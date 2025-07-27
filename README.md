# FeiraTool: Seu Assistente de Listas de Compras 🛒

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)

Cansado de chegar ao caixa do supermercado e ter uma surpresa com o valor total da compra? O **FeiraTool** nasceu para resolver esse problema, ajudando você a planejar suas compras, definir um orçamento e saber o custo total dos seus itens antes mesmo de sair de casa.

Esta é a versão inicial do projeto, focada em fornecer as ferramentas essenciais para um controle de compras eficiente e sem estresse.

## ✨ Funcionalidades da v1.0

* **Crie e Gerencie Listas**: Crie quantas listas de compras você precisar, com nomes personalizados.
* **Defina um Orçamento**: Atribua um valor de orçamento para cada lista e acompanhe seu saldo.
* **Adicione Itens Facilmente**: Insira produtos com nome, quantidade e preço unitário de forma rápida e intuitiva.
* **Acompanhe os Gastos em Tempo Real**: O valor total da sua compra e o saldo do orçamento são atualizados automaticamente a cada item adicionado, editado ou removido.
* **Exclua com Gestos Simples**:  Arraste para o lado para deletar itens ou listas inteiras com um diálogo de confirmação para evitar acidentes.
* **Interface Limpa e Intuitiva**: Construído com Material 3, o FeiraTool oferece uma experiência de usuário moderna e agradável.

## 🛠️ Tecnologias Utilizadas

Este aplicativo foi construído utilizando as tecnologias mais modernas do ecossistema Android:

* **Linguagem**: [Kotlin](https://kotlinlang.org/)
* **Interface Gráfica**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Arquitetura**: MVVM (Model-View-ViewModel)
* **Gerenciamento de Estado**: StateFlow e Coroutines
* **Persistência de Dados**: [Room Database](https://developer.android.com/training/data-storage/room)
* **Design System**: [Material 3](https://m3.material.io/)

## 🚀 Como Baixar e Instalar

Existem duas maneiras de testar o aplicativo em seu dispositivo.

### Para Desenvolvedores

Se você deseja compilar o projeto a partir do código-fonte:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/billjsan/feiratool
    ```
2.  **Abra no Android Studio:**
    * Abra o Android Studio (versão Hedgehog ou mais recente).
    * Selecione "Open an existing project" e navegue até a pasta do projeto clonado.
3.  **Compile e Execute:**
    * Aguarde o Gradle sincronizar as dependências.
    * Selecione um emulador ou conecte um dispositivo Android via USB.
    * Clique no botão "Run 'app'".

### Para Usuários (Instalando o APK)

Você pode instalar o aplicativo diretamente no seu celular Android.

1.  **Baixe o APK**: Vá para a seção **Releases** deste repositório e baixe o arquivo `FeiraTool-v1.0.0.apk`.
2.  **Permita a Instalação**: No seu celular, talvez seja necessário permitir a instalação de aplicativos de "fontes desconhecidas". Vá em **Configurações > Segurança** e habilite a opção.
3.  **Instale**: Abra o arquivo `.apk` que você baixou e siga as instruções para instalar.

## 🔮 Visão de Futuro

O objetivo inicial do FeiraTool é simples, mas a visão é grande. As próximas versões planejam incluir:

* Histórico de preços dos produtos.
* Sugestões inteligentes de itens com base em compras anteriores.
* Compartilhamento de listas com outras pessoas.
* Gráficos e relatórios de gastos.

Sinta-se à vontade para abrir uma *issue* com sugestões ou reportar bugs!
