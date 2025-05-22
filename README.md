# Fast Food Payment

O projeto gerencia o fluxo de pagamento de uma lanchonete.

## Sumário

- [Descrição](#descrição)
- [Instalação](#instalação)
- [Uso](#uso)
- [Endpoints de Testes](#endpoints-de-testes)
- [Autores e Reconhecimentos](#autores-e-reconhecimentos)

## Descrição

O projeto tem por objetivo ajudar uma lanchonete em processo de expansão,
provendo um sistema de autoatendimento de fast-food.

O projeto consegue validar o pagamento a partir de um evento, e salva o id do pedido.

## Instalação e Uso

Instruções para instalação do projeto.

```bash
# Clone o repositório
git clone https://github.com/beraldoLucca/fast-food-payment.git

# Navegue até o diretório do projeto
cd fast-food-payment

# Empacote o projeto, usando o comando abaixo, para que seja gerado a versão:
mvn package

# Execute o comando abaixo para rodar a aplicação:
docker-compose up --build

# Depois da aplicação estar rodando, acesse a URL do Swagger:
http://localhost:8081/api

# Para ver o documento de open-api, acesse a URL:
http://localhost:8081/api-docs
```

## Endpoints de Testes

### Introdução
Aqui estão os endpoints da API que você pode usar para testar as funcionalidades.


### Endpoints principais:

-   ### Webhook para confirmação/recusa de pagamento
    - #### URL: /payment/{event}/order/{id}
        - exemplo: payment/payment_approved/order/1
    - #### Método: `GET`
    - #### Resposta:
            True or False

## Autores e Reconhecimentos

Lucca Beraldo

