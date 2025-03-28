def solution(s):
    cnt = 0
    for char in s:
        if char == "(":
            cnt += 1
        else:
            cnt -= 1
        if cnt < 0:  # 닫는 괄호가 먼저 나온 경우
            return False
    return cnt == 0
