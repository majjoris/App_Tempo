# Aplicativo de Clima
![GitHub repo size](https://img.shields.io/github/repo-size/iuricode/README-template?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/iuricode/README-template?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/iuricode/README-template?style=for-the-badge)

Uma aplicação para visualização o clima usando Java Swing feito na Eclipse IDE.
# Screenshots
![image](https://github.com/majjoris/App_Tempo/assets/49294802/84b0a321-dd01-4e23-a738-b92bbcd45ed8)

![image](https://github.com/majjoris/App_Tempo/assets/49294802/877cfe1f-e491-4ade-80ab-54e82c71a37e)

# 💻 Pré-requisitos

* Java 8 JDk ou superior 
* Postgresql 15 ou superior

# Instalação 🔌
1. Pegue sua chave API [aqui](https://www.visualcrossing.com/weather/weather-data-services)

2. Clone o repositorio 
```sh
   git clone https://github.com/majjoris/App_Tempo.git
   ```
3. Importe-o no Eclipse IDE com o Maven ou em qualquer outra IDE java

4. Insira a chave API em `Inicio.java` linha 97
```sh
    urll.setChave_api("");//insira sua chave aqui
```
5. Crie um banco de dado usando os comandos do arquivo `Criar_bd.txt`

6. Faça a conexão com o banco de dado em `CinexaoBD.java` linha 20
```sh
    conn = DriverManager.getConnection("");
```
7. Execute o aplicativo :D

# Contribuições 💡
Se você deseja contribuir com este projeto e torná-lo melhor com novas ideias, seu pull request será muito bem-vindo. Se você encontrar algum problema, basta colocá-lo na seção de problemas do repositório, obrigado.