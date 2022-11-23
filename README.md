# mazes-kotlin
Kotlin implementations of the mazes code from "Mazes for Programmers" book (by Jamis Buck)

## A 10x10 grid with distances relative to (0,0)
```
▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜
▏ 0    1    2    3    4    5    6    7    8    9 ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏ 1 ▕▏ 2 ▕▏ b    a    9    8    7 ▕▏ c    b    a ▕
▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏ 2 ▕▏ 3 ▕▏ c ▕▏ b ▕▏ a    9 ▕▏ 8 ▕▏ d ▕▏ c    b ▕
▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜
▏ 3 ▕▏ 4 ▕▏ d    c ▕▏ h    g    f    e ▕▏ d ▕▏ c ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜
▏ 6    5 ▕▏ k    j    i    h    g ▕▏ f ▕▏ e ▕▏ d ▕
▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜
▏ 7 ▕▏ m    l ▕▏ k    j    i    h ▕▏ g    f ▕▏ e ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏ o    n    m ▕▏ l ▕▏ k    j    i    h    g ▕▏ f ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜
▏ p    o    n ▕▏ m    l ▕▏ k    j    i ▕▏ h    g ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏ q ▕▏ p    o    n    m ▕▏ l    k    j    i    h ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏ r    q ▕▏ p    o ▕▏ n ▕▏ m ▕▏ l ▕▏ k ▕▏ j    i ▕
▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟

```
## The above grid with an optimal Dijkstra path from (0, 0) to (8, 6)

```
▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜
▏ 0    1    2    3    4    5    6    7    8    9 ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏   ▕▏   ▕▏                       ▕▏           a ▕
▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏   ▕▏   ▕▏   ▕▏   ▕▏        ▕▏   ▕▏   ▕▏      b ▕
▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜
▏   ▕▏   ▕▏        ▕▏                  ▕▏   ▕▏ c ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜
▏        ▕▏                       ▕▏   ▕▏   ▕▏ d ▕
▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜
▏   ▕▏        ▕▏                  ▕▏        ▕▏ e ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏             ▕▏   ▕▏                       ▕▏ f ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜
▏             ▕▏        ▕▏             ▕▏      g ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏   ▕▏                  ▕▏      k    j    i    h ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏        ▕▏        ▕▏   ▕▏   ▕▏   ▕▏   ▕▏        ▕
▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟


```

## The above grid with one of the Dijkstra longest paths from (0, 0)

```
▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜
▏ 0    1    2    3    4    5    6    7    8    9 ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏   ▕▏   ▕▏                       ▕▏           a ▕
▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏   ▕▏   ▕▏   ▕▏   ▕▏        ▕▏   ▕▏   ▕▏ c    b ▕
▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜
▏   ▕▏   ▕▏        ▕▏                  ▕▏ d ▕▏   ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜
▏        ▕▏                       ▕▏   ▕▏ e ▕▏   ▕
▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜
▏   ▕▏        ▕▏                  ▕▏      f ▕▏   ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏             ▕▏   ▕▏ k    j    i    h    g ▕▏   ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜
▏             ▕▏      l ▕▏             ▕▏        ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏   ▕▏ p    o    n    m ▕▏                       ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏ r    q ▕▏        ▕▏   ▕▏   ▕▏   ▕▏   ▕▏        ▕
▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟

```

## A 20x20 grid showing cell visitation using the Aldous Broder algorithm.

```
▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜
▏ s ▕▏ p    o    n    m    l    k    j    i    h    g    h ▕▏ o    n    m    l    k    j    i ▕▏ j ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏ r    q ▕▏ p ▕▏ m    l    k    j ▕▏ i    h ▕▏ i ▕▏ f ▕▏ q    p ▕▏ e    d    e    f    g    h    i ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜
▏ q    p    o    n    o    p ▕▏ i    h    g    f    e ▕▏ d    e ▕▏ b    c    d ▕▏ m ▕▏ h    i    j ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜
▏ r    q    r ▕▏ w    x ▕▏ q ▕▏ v    u ▕▏ v ▕▏ e    d    c ▕▏ f ▕▏ a ▕▏ d ▕▏ e ▕▏ l ▕▏ i ▕▏ l ▕▏ m ▕
▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟
▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜
▏ s    r ▕▏ s ▕▏ v    u ▕▏ r    s    t    u ▕▏ f ▕▏ e ▕▏ b    a ▕▏ 9 ▕▏ e ▕▏ f ▕▏ k    j ▕▏ k    l ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏ t    u ▕▏ 1e   1f▕▏ t    s ▕▏ v ▕▏ 13▕▏ h    g    f ▕▏ a    9    8    9 ▕▏ g    h    i    j ▕▏ m ▕
▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏ u ▕▏ 1c   1d▕▏ 1g   1h▕▏ t    u ▕▏ 12   11   12   13▕▏ b ▕▏ 6    7 ▕▏ k    j    i    j    k ▕▏ n ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏ 1c   1b   1a▕▏ 1h▕▏ 10   y ▕▏ v ▕▏ y    10▕▏ 13▕▏ 5    4    5 ▕▏ 8 ▕▏ l ▕▏ k    l    m ▕▏ l ▕▏ o ▕
▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟
▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜
▏ 1d   1e▕▏ 19   18▕▏ y    x    w    x ▕▏ 11   12▕▏ 6 ▕▏ 3 ▕▏ 6 ▕▏ 9    a ▕▏ l ▕▏ m    n    o ▕▏ t ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜
▏ 1e   1f   1g▕▏ 17   16   15   14   13   12▕▏ 2    1    2 ▕▏ 7 ▕▏ 6 ▕▏ 7    8 ▕▏ p    o ▕▏ p ▕▏ s ▕
▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟
▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜
▏ 1f   1g   1h▕▏ 18   19▕▏ 16▕▏ 15   14   13▕▏ 1    0    1 ▕▏ 4    5    6    7 ▕▏ q    p ▕▏ q    r ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜
▏ 1k   1j   1i▕▏ 1b   1a▕▏ 17   18▕▏ i ▕▏ 9    8    7 ▕▏ 2    3    4    5    6    7 ▕▏ s    r ▕▏ u ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜
▏ 1l▕▏ 1k   1l   1m▕▏ 1b▕▏ 18   19▕▏ h ▕▏ a    b ▕▏ 6    5    4 ▕▏ 5 ▕▏ a    9    8 ▕▏ t ▕▏ s    t ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏ 1m   1l▕▏ 1e   1d   1c   1d▕▏ f    g ▕▏ b ▕▏ q    p    o ▕▏ 5 ▕▏ c    b    a    b ▕▏ e ▕▏ v    u ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜
▏ 1p▕▏ 1m   1n   1o   1p▕▏ 1e▕▏ e    d    c ▕▏ r ▕▏ m    n ▕▏ 6 ▕▏ 9 ▕▏ c    b    c    d ▕▏ g ▕▏ j ▕
▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟
▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜
▏ 1o   1n▕▏ j    i    h    g    f ▕▏ i ▕▏ j ▕▏ k    l    m ▕▏ 7    8    9    a ▕▏ d ▕▏ e    f ▕▏ i ▕
▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜
▏ y    x ▕▏ k ▕▏ j ▕▏ u    v ▕▏ g    h    i    j    k    l ▕▏ q ▕▏ j ▕▏ a ▕▏ b    c    d ▕▏ i    h ▕
▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟
▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜
▏ 10▕▏ w    v    u    t    s ▕▏ h    i    j    k ▕▏ n    o    p ▕▏ i ▕▏ d    c ▕▏ d    e    f    g ▕
▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙   ▟▙▁▁▁▟▙▁▁▁▟
▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛   ▜▛▔▔▔▜▛▔▔▔▜
▏ y    x ▕▏ 13▕▏ v ▕▏ u ▕▏ r ▕▏ q    r ▕▏ m    l    m    n ▕▏ q ▕▏ h ▕▏ e    d ▕▏ g    f    g    h ▕
▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙   ▟▙   ▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙   ▟▙▁▁▁▟
▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛   ▜▛   ▜▛▔▔▔▜▛▔▔▔▜▛▔▔▔▜▛   ▜▛▔▔▔▜
▏ 10   11   12▕▏ w    x ▕▏ q    p    o    n ▕▏ m    n    o ▕▏ r ▕▏ g    f    g    h    i ▕▏ h    i ▕
▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟▙▁▁▁▟

```
