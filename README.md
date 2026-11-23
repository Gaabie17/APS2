# APS2 — Sistema de Controle de Condomínios

Aplicação em Java, executada pelo terminal, para controle de condomínios, seus edifícios, apartamentos e moradores.

Trabalho da Atividade Avaliativa N2 de **Análise e Projeto de Sistemas II**, do Curso Superior de Tecnologia em Análise e Desenvolvimento de Sistemas do IFRS, Campus Feliz.

## Funcionalidades

| Opção do menu | Caso de uso |
| --- | --- |
| 1 - Manter condomínios (incluir, consultar, alterar, excluir) | UC01 |
| 2 - Manter edifícios | UC02 |
| 3 - Manter apartamentos (Padrão ou Luxo) | UC03 |
| 4 - Manter moradores | UC04 |
| 5 - Consultar renda média de um apartamento | UC05 |
| 6 - Listar condomínios em árvore | UC06 |

Os casos UC07 (Localizar condomínio), UC08 (Localizar edifício) e UC09 (Localizar apartamento) são incluídos pelos casos acima.

- Na alteração, cada campo mostra o valor atual entre colchetes; apertar Enter mantém o valor.
- Toda exclusão pede confirmação e remove também tudo o que o elemento contém.

## Regras do domínio

- Um condomínio (número, nome, cidade, bairro) tem vários edifícios.
- Um edifício (número, cores de pintura, quantidade de andares) tem vários apartamentos.
- Um apartamento é identificado pelo número e pelo andar, e pode ser:
  - **Padrão**: tipo de armários e tipo de pisos;
  - **Luxo**: modelos de luminárias, geladeira embutida (sim/não) e fogão embutido (sim/não).
- Um morador tem nome, CPF, RG, sexo, idade e renda.
- A renda média do apartamento é a soma das rendas dos moradores dividida pela quantidade de moradores.

## Padrões de projeto

| Padrão | Onde | Por quê |
| --- | --- | --- |
| **Composite** | `ComponenteCondominio`, implementado por `Condominio`, `Edificio`, `Apartamento` e `Morador` | A hierarquia condomínio → edifício → apartamento → morador é uma árvore. A renda média e a listagem são calculadas recursivamente pela mesma interface. |
| **Factory** | `ApartamentoFactory` e `TipoApartamento` | Cria um apartamento Padrão ou Luxo a partir do tipo escolhido, sem que o menu conheça as classes concretas. |
| **Singleton** | `CondominioRepository` | Garante um único repositório de dados, compartilhado por todas as telas. |

## Estrutura do projeto

```
src/
├── Main.java
├── model/
│   ├── ComponenteCondominio.java   (Composite: componente)
│   ├── Condominio.java             (Composite: composto)
│   ├── Edificio.java               (Composite: composto)
│   ├── Apartamento.java            (Composite: composto, abstrata)
│   ├── ApartamentoPadrao.java
│   ├── ApartamentoLuxo.java
│   └── Morador.java                (Composite: folha)
├── factory/
│   ├── ApartamentoFactory.java     (Factory)
│   └── TipoApartamento.java
├── repository/
│   └── CondominioRepository.java   (Singleton)
└── view/
    ├── Menu.java                   (menu principal)
    ├── MenuCadastro.java           (submenu comum, abstrata)
    ├── MenuCondominio.java         (UC01)
    ├── MenuEdificio.java           (UC02)
    ├── MenuApartamento.java        (UC03)
    ├── MenuMorador.java            (UC04)
    ├── Localizador.java            (UC07, UC08, UC09)
    └── Entrada.java                (leitura do teclado)
```

## Como executar

Requisito: JDK 17 ou superior.

**IntelliJ IDEA:** abra a pasta do projeto e execute a classe `Main`.

**Terminal**, na pasta do projeto:

```bash
javac -encoding UTF-8 -d out src/Main.java src/model/*.java src/factory/*.java src/repository/*.java src/view/*.java
java -cp out Main
```

Os dados ficam em memória e são perdidos ao encerrar o programa.

## Exemplo de saída (opção 6)

```
- Condomínio 1: Residencial Vale | Centro, Feliz | Edifícios: 1 | Moradores: 2
    - Edifício 10 | Cores: Branco, Azul | Andares: 5 | Apartamentos: 2
        - Apartamento 101 (andar 1) | Padrão | Armários: MDF | Pisos: Porcelanato | Renda média: R$ 2500,25
            - Morador: Ana | CPF: 111 | RG: 222 | Sexo: F | Idade: 30 | Renda: R$ 3000,00
            - Morador: Joao | CPF: 333 | RG: 444 | Sexo: M | Idade: 35 | Renda: R$ 2000,50
        - Apartamento 501 (andar 5) | Luxo | Luminárias: Pendente LED, Plafon | Geladeira embutida: sim | Fogão embutido: não | Renda média: R$ 0,00
            (sem moradores)
```
