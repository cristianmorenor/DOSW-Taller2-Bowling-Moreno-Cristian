# DOSW Taller 2 - Bowling TDD

## 1. Identificación
- **Nombre completo:** Cristian Santiago Moreno Ruiz
- **Código estudiantil:** 1000100162
- **Correo institucional:** cristian.moreno-r@mail.escuelaing.edu.co

## 2. Descripción

BowlTech S.A.S. quiere digitalizar el sistema de puntuación de sus pistas de bolos. El sistema manual actual olvida los bonos de strike, confunde los spares y se equivoca calculando el juego perfecto. Este proyecto construye el motor de puntuación de un juego de Bowling aplicando Test-Driven Development (TDD) desde cero.

**Reglas implementadas:**
- Tiro normal: puntúan solo los pinos derribados en ese tiro.
- Spare (10 pinos en 2 intentos del mismo frame): 10 + el primer tiro del siguiente frame.
- Strike (10 pinos en el primer intento): 10 + la suma de los dos tiros siguientes.
- Frame 10: si hay strike o spare, se permiten hasta 3 tiros en ese frame.
- Juego perfecto: 12 strikes consecutivos = 300 puntos.

**Responsabilidad de cada clase:**
- `BowlingGame`: orquesta el juego, valida los tiros roll, gestiona la lista de frames y determina si el juego está completo isComplete.
- `Frame`: representa un frame individual; almacena sus tiros, calcula su suma de pinos y determina su tipo (NORMAL, SPARE, STRIKE, TENTH).
- `FrameType`: enum que clasifica el tipo de un frame.
- `BowlingScorer`: clase sin estado que recibe la lista de frames ya jugados y calcula el puntaje total aplicando los bonos de spare y strike.

## 3. Evidencia TDD

**RED** — caso A5, `roll()` debe lanzar `IllegalStateException` cuando el juego ya está completo (antes de implementar `isComplete()`):

![Test en rojo](docs/evidence/tdd-red.png)

**GREEN** — después de implementar `isComplete()` y `validateGameNotComplete()`, con los 8 casos del Módulo A pasando:

![Tests en verde](docs/evidence/tdd-green.png)

## 4. JaCoCo - Cobertura de código

La cobertura se alcanzó de forma natural aplicando TDD estricto en los tres módulos (A, B y C) — no fue necesario escribir pruebas adicionales dedicadas solo a subir el porcentaje, ya que los 22 tests que verifican el comportamiento (validaciones, strike, spare, frame 10, cálculo de puntaje, `isComplete()`) ya cubrían casi todo el código de producción.

**Resultado de mvn clean verify:**

| Métrica | Cobertura |
|---------|-----------|
| Instrucciones | 99% |
| Branches | 95% |
| Líneas | ~98% (89 de 91 líneas) |

![Reporte JaCoCo](docs/evidence/JacocoCoberage.png)

Las clases más ejercitadas fueron `BowlingGame` (por los casos de los Módulos A y C) y `BowlingScorer` (por los casos del Módulo B); las únicas líneas sin cubrir corresponden a ramas muy específicas no exigidas por ningún caso de prueba del taller.
## 5. SonarQube - Análisis estático

**Antes de corregir issues** — 3 issues de mantenibilidad (código sin usar, cadena if/else reemplazable por switch, lambda reemplazable por method reference), cobertura 96.8%:

![Dashboard con issues](docs/evidence/sonarqube-dashboard.png)

**Después de corregir** — 0 issues abiertos en todas las categorías (Security, Reliability, Maintainability), todas con calificación A, cobertura 96.7%, 0% duplicación:

![Dashboard final](docs/evidence/sonarqube-final.png)

**Quality Gate:** Passed (todas las condiciones del gate por defecto "Sonar way" se cumplen: cobertura en código nuevo 100%, 0 issues nuevos, duplicación 0%).
## 6. Pull Requests

| PR | Fecha de merge | Módulo que cubre |
|----|----------------|-------------------|
| [Módulo A: BowlingGame.roll() - TDD completo](https://github.com/cristianmorenor/DOSW-Taller2-Bowling-Moreno-Cristian/pull/1) | 2026-09-17     | Módulo A - `roll()` |
| [Módulo B: BowlingScorer.calculate() - TDD completo](https://github.com/cristianmorenor/DOSW-Taller2-Bowling-Moreno-Cristian/pull/2) | 2026-09-17     | Módulo B - `BowlingScorer.calculate()` |
| [Módulo C: BowlingGame.isComplete() - TDD completo](https://github.com/cristianmorenor/DOSW-Taller2-Bowling-Moreno-Cristian/pull/3) | 2026-09-17     | Módulo C - `isComplete()` |
| [Parte 4: Cobertura JaCoCo y analisis SonarQube + fixes de calidad](https://github.com/cristianmorenor/DOSW-Taller2-Bowling-Moreno-Cristian/pull/4) | 2026-09-17     | Parte 4 - JaCoCo y SonarQube |

## 7. Reflexión técnica

## 7. Reflexión técnica

**¿Qué aprendiste sobre TDD al desarrollar este proyecto?**

Antes de este taller tenía la idea de que TDD era básicamente "escribir el test antes que el código porque toca", casi como un trámite. Haciendo el ejercicio con Bowling me di cuenta de que en realidad cambia la forma en que uno piensa el problema: antes de escribir una sola línea de `BowlingGame` o `BowlingScorer` tenía que preguntarme qué debía pasar en cada caso, incluyendo los raros (un strike en el décimo frame, dos strikes seguidos, un juego perfecto). Eso me obligó a entender la lógica del bowling a fondo antes de programar, y no al revés. También noté que el ciclo RED-GREEN-REFACTOR evita que uno se adelante a "resolver todo de una" — primero hago que pase el caso más simple, y solo cuando ya está verde me preocupo por dejar el código bien escrito.

**¿En qué casos los tests pasaron "de primera" sin necesidad de nuevo código, y por qué es correcto?**

Esto me pasó justo en el módulo B, después de refactorizar el cálculo de puntaje en el caso B4. Ahí generalizamos el algoritmo para que manejara strikes, spares y frames normales de forma unificada, en lugar de tener casos especiales sueltos. Cuando después agregué nuevos casos de prueba, varios pasaron sin tener que tocar el código de `BowlingScorer`. Al principio me generó dudas — pensé que quizás algo estaba mal planteado — pero entendí que esto es justo lo que se espera cuando el algoritmo ya quedó bien generalizado: es lo que en TDD se llama triangulación. Es distinto a que un test pase "de gratis" por una implementación hardcodeada o por casualidad; acá pasaba porque la lógica general ya cubría ese comportamiento correctamente.

**¿Qué aporta JaCoCo como herramienta de cobertura, más allá del porcentaje?**

Al principio solo miraba el número final de cobertura, pero JaCoCo da mucho más que eso: te muestra visualmente, línea por línea y rama por rama, qué partes del código nunca se ejecutaron durante los tests. Eso fue útil para confirmar que el ~97-99% de cobertura que obtuvimos no era "hueco" — es decir, no era cobertura que viniera de tests triviales que solo tocan el código sin validar nada, sino que realmente correspondía a los casos de negocio que fuimos cubriendo con TDD. También sirvió como una especie de checklist visual: si veía una línea en rojo, era una señal de que me faltaba pensar en algún caso límite.

**¿Qué valor aportó SonarQube al análisis del código?**

SonarQube fue el que me hizo caer en cuenta de que "todos los tests pasan y la cobertura está alta" no es lo mismo que "el código está bien escrito". Encontró cosas que ni los tests ni yo habíamos notado: un campo que quedó sin usar, un if/else que se podía expresar de forma más clara con un switch, y una lambda donde en realidad convenía usar una referencia a método. Ninguno de esos problemas rompía la funcionalidad, pero sí afectaban la legibilidad y el mantenimiento del código a futuro. Me sirvió para entender que la calidad de software no se mide solo con pruebas, sino también con este tipo de análisis estático que revisa buenas prácticas y estilo.