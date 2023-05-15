# Resumo

Projeto de urna para votação feita em Java utilizando oparadigma de POO(Programação Orientad a Objetos). 

Versão JAVA: 19 ou superior.

# Inicialização

Para iniciar o sistema executar o arquivo <code>main.java</code> na pasta raiz

# Login

Para inicio do sistema é necessário o login do mesario responsavel pela eleição, há o registro salvo para facilitar o uso sendo:

USER: "mesario"<br>
SENHA: "123456"

# Features

## Cadastro de eleitores

NA tela inicial no menu superior é possivel cadastrar um novo CPF apto a votar, ao inserir aparecerá o alerta indicando se foi possivel inserir o CPF ou se ele já está em uso

## Novo voto

Ainda na tela inicial há o botão de votar, ao clicar pedirá a confirmação do CPF para verificação se está cadastrado ou se já votou, confirmado qualquer um desses pontos não é possível votar.

Cetifaco de que é o primeiro voto do CPF informado será exibida a tela de votação com três imagens referentes aos candidatos disponiveis segudas de seus respectivos nomes. Clicando em qualquer uma dessas imagens o voto é computado e aparece a tela de confirmação onde é possível voltar para a tela de confirmação de CPF para realizar um novo voto ou com a senha do mesario finalizar a eleição e exibir os resultados.