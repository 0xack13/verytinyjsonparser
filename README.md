# verytinyjsonparser
Very tiny incomplete JSON parser

Just trying to write a simple and tiny JSON parser that parses string "key/value" with less than 300 lines.

Summary of the parser's execution:

```
- JSONParser (takes a StringParserInput).Parse()
- Parse -> ParseObject
- StringParserInput is an implementaiton of ParseInput (read method)
- JSONObject is just a structured store for the JSON objects e.g. HashMap kv
```
