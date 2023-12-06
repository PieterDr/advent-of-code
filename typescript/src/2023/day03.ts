import {getInput} from "./util";

const example1 = [
  "467..114..",
  "...*......",
  "..35..633.",
  "......#...",
  "617*......",
  ".....+.58.",
  "..592.....",
  "......755.",
  "...$.*....",
  ".664.598..",
];

const example2 = [
  "12.......*..",
  "+.........34",
  ".......-12..",
  "..78........",
  "..*....60...",
  "78.........9",
  ".5.....23..$",
  "8...90*12...",
  "............",
  "2.2......12.",
  ".*.........*",
  "1.1..503+.56",
];

const example3 = [
  ".......5......",
  "..7*..*.....4*",
  "...*13*......9",
  ".......15.....",
];

part1(getInput("day03"))
part2(getInput("day03"))

function part1(input: string[]) {
  const result = input
    .map((line, idx) => ({line: line, lineIdx: idx, numbers: line.match(/\d+/g) as RegExpMatchArray}))
    .filter(line => !!line.numbers)
    .map(line => {
      return line.numbers
        .map(number => {
          const startIdx = line.line.indexOf(number);
          const endIdx = startIdx + number.length

          const above = getSubString(Math.max(0, line.lineIdx - 1), Math.max(0, startIdx - 1), Math.min(line.line.length, endIdx + 1));
          const along = getSubString(line.lineIdx, Math.max(0, startIdx - 1), Math.min(line.line.length, endIdx + 1));
          const below = getSubString(Math.min(input.length - 1, line.lineIdx + 1), Math.max(0, startIdx - 1), Math.min(line.line.length, endIdx + 1));

          const symbols = (above + along + below).replaceAll(".", "").replaceAll(/\d+/g, "");
          line.line = line.line.replace(number, number.replaceAll(/\d/g, ".")) // wipe number from line so duplicate number uses correct startIdx
          return symbols.length > 0 ? Number.parseInt(number) : 0;
        })
        .reduce((a, b) => a + b, 0);
    })
    .reduce((a, b) => a + b, 0);

  console.log(result)

  function getSubString(lineIdx: number, startIdx: number, endIdx: number): string {
    return input[lineIdx].substring(startIdx, endIdx);
  }
}

function part2(input: string[]) {
  const map: Map<string, number[]> = new Map();
  let result = 0;
  input
    .map((line, idx) => ({line: line, lineIdx: idx, numbers: line.match(/\d+/g) as RegExpMatchArray}))
    .filter(line => !!line.numbers)
    .map(line => line.numbers
      .map(number => {
          const startIdx = line.line.indexOf(number);
          const endIdx = startIdx + number.length - 1
          line.line = line.line.replace(number, number.replaceAll(/\d/g, ".")) // wipe number from line so duplicate number uses correct startIdx
          findAsterisks(line.lineIdx);
          if (line.lineIdx > 0) findAsterisks(line.lineIdx - 1);
          if (line.lineIdx < input.length - 1) findAsterisks(line.lineIdx + 1);

          function findAsterisks(lineIdx: number) {
            input[Math.max(0, lineIdx)].split("")
              .map((char, idx) => ({char: char, idx: idx}))
              .filter(char => char.char === "*" && Math.max(0, startIdx - 1) <= char.idx && char.idx <= Math.min(line.line.length, endIdx + 1))
              .forEach(char => {
                const coord = JSON.stringify({line: lineIdx, pos: char.idx});
                if (map.has(coord)) {
                  console.log(`Found ${map.get(coord)} at ${coord} for ${number}`)
                  map.get(coord)?.push(Number.parseInt(number));
                } else {
                  console.log(`Setting ${number} at ${coord}`)
                  map.set(coord, [Number.parseInt(number)]);
                }
              });
          }
        }
      ));
  for (const numbers of map.values()) {
    if (numbers.length === 2) {
      result += numbers[0] * numbers[1];
    }
  }

  console.log(result);
}
