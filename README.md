# Itaú Desafio — API de Transações e Estatísticas

API REST em Java/Spring Boot que recebe transações financeiras e calcula estatísticas (contagem, soma, média, mínimo e máximo) considerando apenas os últimos 60 segundos.

## Tecnologias

Java, Spring Boot, Lombok, `CopyOnWriteArrayList` (armazenamento em memória, thread-safe).

## Endpoints

**`POST /transacao`** — registra uma transação
```json
{ "valor": 123.45, "dataHora": "2026-09-09T10:15:30-03:00" }
```
- `201` sucesso · `422` valor negativo ou data futura · `400` JSON inválido

**`DELETE /transacao`** — remove todas as transações (`200`)

**`GET /estatistica`** — estatísticas do último minuto
```json
{ "count": 10, "sum": 1250.50, "avg": 125.05, "min": 10.00, "max": 300.00 }
```
Sem transações no período, retorna tudo zerado.

## Como executar

```bash
git clone https://github.com/ccamposhe/itau-desafio.git
cd itau-desafio
./mvnw spring-boot:run
```

```bash
curl -X POST localhost:8080/transacao -H "Content-Type: application/json" \
  -d '{"valor": 100.00, "dataHora": "2026-09-09T10:00:00-03:00"}'
curl localhost:8080/estatistica
curl -X DELETE localhost:8080/transacao
```

---
## 👤 Autor

Desenvolvido por **Carlos Henrique Campos**.

💼 LinkedIn: [Ccamposhe](https://www.linkedin.com/in/ccamposhe/)
💻 GitHub: [@Ccamposhe](https://github.com/Ccamposhe)
