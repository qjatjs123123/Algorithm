import sys



for test_case in range(1):
    n = int(sys.stdin.readline())
    budget = list(map(int, sys.stdin.readline().split()))
    total_budget = int(sys.stdin.readline())

    if sum(budget) <= total_budget:
        print(max(budget))
    else:
        start = 0
        end = max(budget)

        while start <= end:
            mid = (start + end) // 2

            total = 0

            for num in budget:
                if mid >= num:
                    total += num
                else:
                    total += mid

            if total > total_budget:
                end = mid - 1
            else:
                start = mid + 1

        print(end)