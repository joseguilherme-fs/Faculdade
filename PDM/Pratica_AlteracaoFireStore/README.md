# AppCalmaria - Banco Firestore

## Disciplina: Programação para Dispositivos Móveis - 2024.2 (23 de Fevereiro de 2025)  
**Professor:** Gustavo Wagner Diniz Mendes  

### Prática: Alterações Firestore 

Partindo como base o código abaixo, faça:
- Crie um banco de dados **Firestore**, utilizando o tutorial anterior;
- Dentro do banco de dados, crie uma coleção, chamada usuarios. Insira pelo menos um documento (usuário), com nome e senha iguais. Crie pelo menos mais dois usuários;
- Siga os passos de como colocar o Firestore na sua app. No código abaixo já está com o google-services.json da minha conta. Sobrescrevam esse arquivo com o novo criado no seu banco. Os passos sobre importação de biblioteca não precisam ser feitos, já que esse projeto abaixo já contempla (mas é importante analisar e entender);
- Execute a aplicação no seu celular e veja que está funcionando: loga se nome/senha existirem  e forem iguais no banco Firestore, e lista, na tela principal, se clicar em Carregar, todos os usuários cadastrados;
- Teste colocar um nome de usuário diferente da senha e veja a mensagem num Toast;
- Abra o UsuarioDAO e implemente os métodos solicitados (busque por TODO);
- Altere o objeto usuário para ter a anotacao @DocumentId em cima do atributo Id. Isso fará que, automaticamente, o Id do documento do Firestore seja mapeado para o Id do objeto. Para saber mais sobre isso, leia o link anexo;
- Crie uma nova tela, a de cadastro, e na tela de login, depois de ter criado a opção de "Cadastrar", navegue para a tela de cadastro. Essa tela deve pegar os dados digitados e chamar o UsuarioDAO para cadastrar. Após usuário cadastrado com sucesso, navegue para tela de Login;
- Tente logar com o usuário cadastrado;
- Na tela principal (TelaPrincipal), altere o Card que está em LazyColumn (procure por TODO) para ficar mais elegante da forma que se pede. Veja que usamos um LazyColumn pois não sabemos a quantidade de usuários do banco, e o LazyColumn só carregará o que está visível na tela, ou seja, mostrará e carregará os elementos à medida que o usuário rola na tela.
