def solution(s):
    nums = list(map(int, s.split()))
    return f"{min(nums)} {max(nums)}"
# s.split() : 문자열을 공백 기준으로 나눔 → ["1", #"2", "3", "4"]
# map(int, ...) : 문자열들을 정수로 변환 → [1, 2, 3, 4]
# min(nums), max(nums) : 최솟값과 최댓값 계산
# f"{min} {max}" : 문자열로 조합