# gerenciamento-processos-java-back-end

Para rodar esse projeto, é necessário ter uma instancia de um banco Postgresql com o nome db_gerenc_proces.

E para criar o ambiente com o SGBD e o schema necessário, basta rodar o comando abaixo no terminal dentro da pasta raiz do projeto. 

```markdown
docker-compose -f docker-kafka.yml up
```

Também na raiz é possivel rodar uma jar do projeto para testar a aplicação caso não tenha o ambiente de desenvolvimento Java.

Para isso basta rodar o comando abaixo no terminar dentro da pasta raiz do projeto.

```markdown
java -jar gerenciamento-processos-java-back.jar
```

Também na raiz segue um arquivo exportado do postman com os endpoint existente no projeto. Para testa-lo, basta importalo para o Postman.

```markdown
Aplicação Gerenciamento de Processos.postman_collection.json
```
