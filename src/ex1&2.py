# Contadores globais 
rec_calls = 0
itr_ops = 0
mem_calls = 0

# Fibonacci cálculado com recursão
def calcFibonacciRec (n):
    global rec_calls
    rec_calls += 1
    if n <= 1:
        return n
    
    else: 
        a = calcFibonacciRec(n-1)
        b = calcFibonacciRec(n-2)
        return a + b
    
# Fibonacci cálculado com iteração
def calcFibonacci (n):
    global itr_ops

    f = [0]*(n+1)
    f[0] = 0
    if n > 0:
        f[1] = 1

    for i in range(2, n+1): 
        itr_ops += 1
        f[i] =  f[i-1]+f[i-2]

    return f[n]

# Fibonacci cálculado com memoização
def calcFibonacciMem (n):
    f = [-1]*(n+1)
    return lookupFibo(f, n)

def lookupFibo(f, n):
    global mem_calls
    mem_calls += 1

    if f[n] >= 0:
        return f[n]
    
    if n <= 1:
        f[n] = n

    else: 
        f[n] = lookupFibo(f, n-1) + lookupFibo(f, n-2)

    return f[n]

def teste(n):
    global rec_calls, mem_calls, itr_ops

    rec_calls = 0
    mem_calls = 0
    itr_ops = 0

    r1 = calcFibonacciRec(n)
    r2 = calcFibonacci(n)
    r3 = calcFibonacciMem(n)

    return (n, rec_calls, itr_ops, mem_calls, r1)

def main():

    testes = [4, 8, 16, 32]

    print()

    print("".ljust(15), end="")
    for n in testes:
        print(f"n={n}".ljust(15), end="")
    print()

    print("-" * 75)

    # Recursivo
    print("Recursivo".ljust(15), end="")
    for n in testes:
        resultado = teste(n)
        print(f"{resultado[1]}".ljust(15), end="")  # chamadas
    print()

    # Iterativo
    print("Iterativo".ljust(15), end="")
    for n in testes:
        resultado = teste(n)
        print(f"{resultado[2]}".ljust(15), end="")  # iterações
    print()

    # Memoizado
    print("Memoizado".ljust(15), end="")
    for n in testes:
        resultado = teste(n)
        print(f"{resultado[3]}".ljust(15), end="")  # chamadas
    print()

    print("\nResultados Fibonacci:")
    for n in testes:
        print(f"Fib({n}) = {calcFibonacci(n)}")

    print()

if __name__ == "__main__":
    main()

# Observa-se que o algoritmo recursivo apresenta crescimento exponencial no número de chamadas, 
# tornando-se inviável para valores maiores de n. Já os algoritmos iterativo e memoizado 
# apresentam crescimento linear, sendo significativamente mais eficientes. O algoritmo memoizado mantém
#  a estrutura recursiva, porém evita recomputações ao armazenar resultados intermediários.