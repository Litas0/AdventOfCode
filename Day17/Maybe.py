from heapq import heappop, heappush
ll = [[int(y) for y in x] for x in open('Day17/input17.txt').read().strip().split('\n')]
DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]
def inRange(pos, arr):
	return pos[0] in range(len(arr)) and pos[1] in range(len(arr[0]))
def Djikstra(mindist, maxdist):
	q = [(0, 0, 0, -1)]
	seen = set()
	costs = {}
	while q:
		cost, x, y, dd = heappop(q)
		if x == len(ll) - 1 and y == len(ll[0]) - 1:
			return cost
		if (x, y, dd) in seen:
			continue
		seen.add((x, y, dd))
		for direction in range(4):
			costincrease = 0
			if direction == dd or (direction + 2) % 4 == dd:
				continue
			for distance in range(1, maxdist + 1):
				xx = x + DIRS[direction][0] * distance
				yy = y + DIRS[direction][1] * distance
				if inRange((xx, yy), ll):
					costincrease += ll[xx][yy]
					if distance < mindist:
						continue
					nc = cost + costincrease
					if costs.get((xx, yy, direction), 1e100) <= nc:
						continue
					costs[(xx, yy, direction)] = nc
					heappush(q, (nc, xx, yy, direction))
print(Djikstra(1, 3))
print(Djikstra(4, 10))