import sys
for test_case in range(1):
    k, n = map(int, sys.stdin.readline().split())
    arr = []
    for _ in range(k):
        arr.append(int(sys.stdin.readline()))




    def binary_search():
        start = 1
        end = max(arr)
        cnt = 0
        mid = 0
        while start<=end:
            mid = (start+end) // 2

            ans = 0
            for num in arr:
                ans += num // mid

            if ans >= n:
                start = mid + 1
            else:
                end = mid -1

            cnt += 1
        return end

    print(binary_search())