# App-Fast-Food
Este é um aplicativo desenvolvido em Java, utilizando o Android SDK, para uma rede de fast food realizar pedidos de delivery. O aplicativo permite aos usuários visualizar o cardápio, adicionar produtos ao carrinho e finalizar o pedido (checkout).


Funcionalidades
O aplicativo possui as seguintes funcionalidades:

Listagem de Produtos: Na tela inicial, os produtos do cardápio são exibidos em uma lista, contendo o nome, imagem, preço e uma breve descrição de cada item. Os produtos são obtidos através da API: https://free-food-menus-api.onrender.com/.

<div style="display:flex; gap: 10px;">
  <img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/1.jpeg" width="350">
  <img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/2.jpeg" width="350">
</div><br>

Detalhes do Pedido: Ao selecionar um pedido na lista de pedidos, é exibida uma tela com os detalhes completos do pedido, incluindo a lista de produtos, seus respectivos valores e o valor total do pedido.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/3.jpeg" width="350"><br>

Adição ao Carrinho: Ao selecionar um produto na lista, o usuário pode escolher a quantidade desejada para adicionar ao carrinho de compras.

Finalização do Pedido: O aplicativo possui uma tela dedicada para finalizar o pedido, onde o usuário pode revisar os itens selecionados, adicionar informações adicionais e confirmar o pedido.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/4.jpeg" width="350"><br>

Persistência de Pedidos: Todos os pedidos feitos pelo usuário são armazenados em um banco de dados local, garantindo que as informações não sejam perdidas mesmo após fechar o aplicativo.

Listagem de Pedidos: Existe uma tela dedicada para listar os pedidos já feitos, exibindo informações resumidas de cada um, como valor total.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/5.jpeg" width="350"><br>

Detalhes dos Pedidos: Uma tela em que é possível ver todos os produtos feitos naquele pedido.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/6.jpeg" width="350"><br>


Avaliação dos Produtos: Na tela de listagem dos produtos, é exibida a avaliação média do produto, indicada através de uma quantidade de estrelas.

Filtragem por Categoria: O aplicativo permite filtrar os produtos por categoria, como "Burgers", "Sandwiches", "Pizzas", etc., facilitando a navegação no cardápio.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/7.jpeg" width="350"><br>

Pesquisa de Produtos: Existe um campo de pesquisa que permite ao usuário buscar por produtos específicos no cardápio.

<img src="https://github.com/Teixeira007/App-Fast-Food/blob/main/img/8.jpeg" width="350"><br>

Tecnologias Utilizadas
O projeto utiliza as seguintes tecnologias e ferramentas:

- Java
- Android SDK
- API de consumo: https://free-food-menus-api.onrender.com/
- Banco de Dados Local (biblioteca room)
Configuração do Projeto
Para configurar o projeto em seu ambiente de desenvolvimento, siga as instruções abaixo:

Clone este repositório para sua máquina local:
```bash
git clone https://github.com/Teixeira007/App-Fast-Food.git
```

Abra o projeto no Android Studio.

Importe as dependências necessárias e verifique se todas as bibliotecas estão devidamente configuradas.

Execute o aplicativo em um dispositivo ou emulador Android compatível.
