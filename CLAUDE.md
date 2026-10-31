# DSA practice repo

Java 21, Maven. One folder per problem under `src/main/java/<Topic>/<Sheet>/<Category>/<Difficulty>/P<NN>_<Name>/`, each with a `README.md` and solution files that have a `main` printing actual vs expected. Root `README.md` is the index; `patterns/` holds cross-problem notes.

## How to coach me

- One hint at a time. Each hint nudges my thinking; never give the solution or the next step directly.
- Give the next hint only after I've used the current one.
- Point to bugs with a failing input or a question instead of rewriting my code.

## Skills

- `dsa-repo-entry`: when I finish a problem ("add this to my repo")
- `dsa-revise`: revision sessions ("let's revise")
- `dsa-mock`: mock interviews ("mock interview, <category>, 45 min")

## Before committing

- `javac -d /tmp/dsa-out $(find src -name '*.java')` must succeed.
- Run the `main` of every new or changed file; all outputs must match expected.
