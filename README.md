# App-Fast-Food
Este é um aplicativo desenvolvido em Java, utilizando o Android SDK, para uma rede de fast food realizar pedidos de delivery. O aplicativo permite aos usuários visualizar o cardápio, adicionar produtos ao carrinho e finalizar o pedido (checkout).


Funcionalidades
O aplicativo possui as seguintes funcionalidades obrigatórias:

Listagem de Produtos: Na tela inicial, os produtos do cardápio são exibidos em uma lista, contendo o nome, imagem, preço e uma breve descrição de cada item. Os produtos são obtidos através da API: https://free-food-menus-api.onrender.com/.


Detalhes do Pedido: Ao selecionar um pedido na lista de pedidos, é exibida uma tela com os detalhes completos do pedido, incluindo a lista de produtos, seus respectivos valores e o valor total do pedido.


Adição ao Carrinho: Ao selecionar um produto na lista, o usuário pode escolher a quantidade desejada para adicionar ao carrinho de compras.

Finalização do Pedido: O aplicativo possui uma tela dedicada para finalizar o pedido, onde o usuário pode revisar os itens selecionados, adicionar informações adicionais e confirmar o pedido.

Persistência de Pedidos: Todos os pedidos feitos pelo usuário são armazenados em um banco de dados local, garantindo que as informações não sejam perdidas mesmo após fechar o aplicativo.

Listagem de Pedidos: Existe uma tela dedicada para listar os pedidos já feitos, exibindo informações resumidas de cada um, como data e valor total.

Avaliação dos Produtos: Na tela de listagem dos produtos, é exibida a avaliação média do produto, indicada através de uma quantidade de estrelas.

Filtragem por Categoria: O aplicativo permite filtrar os produtos por categoria, como "Burgers", "Sandwiches", "Pizzas", etc., facilitando a navegação no cardápio.

Pesquisa de Produtos: Existe um campo de pesquisa que permite ao usuário buscar por produtos específicos no cardápio.

Tecnologias Utilizadas
O projeto utiliza as seguintes tecnologias e ferramentas:

Java
Android SDK
API de consumo: https://free-food-menus-api.onrender.com/
Banco de Dados Local (biblioteca room)
Configuração do Projeto
Para configurar o projeto em seu ambiente de desenvolvimento, siga as instruções abaixo:

Clone este repositório para sua máquina local:
```bash
git clone https://github.com/Teixeira007/App-Fast-Food.git
```

Abra o projeto no Android Studio.

Importe as dependências necessárias e verifique se todas as bibliotecas estão devidamente configuradas.

Execute o aplicativo em um dispositivo ou emulador Android compatível.
