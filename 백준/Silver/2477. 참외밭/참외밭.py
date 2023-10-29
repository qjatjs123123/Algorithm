import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    width = []
    height = []
    for i in range(6):
        direct, length = map(int, sys.stdin.readline().split())
        if direct == 1 or direct == 2:
            width.append([length, i])
        else:
            height.append([length,i])

    width.sort(reverse=True)
    height.sort(reverse=True)

    MAX_WIDTH_IDX = width[0][1]
    small_height = 9999

    for tmp in height:
        if tmp[1] not in [(MAX_WIDTH_IDX + 5)%6, (MAX_WIDTH_IDX +7) % 6]:
            small_height = tmp[0]

    MAX_HEIGHT_IDX = height[0][1]
    small_width = 9999

    for tmp in width:
        if tmp[1] not in [(MAX_HEIGHT_IDX + 5) % 6, (MAX_HEIGHT_IDX + 7)% 6]:
            small_width = tmp[0]

    rectangle = (width[0][0] * height[0][0]) - small_height*small_width
    print(rectangle*n)