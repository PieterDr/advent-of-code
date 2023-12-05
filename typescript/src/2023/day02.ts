import {getInput, peek} from "./util";

const example = [
  "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
  "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
  "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
  "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
  "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
]

part1(getInput("day02"))
part2(getInput("day02"))

function getAmount(part: string, color: string): number {
  let matches = part.match(new RegExp(`([0-9]*) ${color}`));
  if (matches) {
    return matches.map(match => match.split(" ")[0])
      .map(Number.parseInt)
      .reduce((a, b) => b > a ? b : a, 0)
  }
  return 0;
}

function part1(input: string[]) {
  let result = input
    .map(line => line.split(": ")[1].split("; "))
    .map((parts, idx) => {
      for (const part of parts) {
        let reds = getAmount(part, "red");
        let greens = getAmount(part, "green");
        let blues = getAmount(part, "blue");
        if (reds > 12 || greens > 13 || blues > 14) {
          console.log(part);
          return NaN;
        }
      }
      return idx + 1;
    })
    .filter(Number.isInteger)
    .reduce((a, b) => a + b, 0);
  console.log(result)
}

function part2(input: string[]) {
  let result = input
    .map(line => line.split(": ")[1].split("; "))
    .map((parts) =>
      parts.map(part => {
        let reds = getAmount(part, "red");
        let greens = getAmount(part, "green");
        let blues = getAmount(part, "blue");
        return {reds, greens, blues};
      }).reduce((a, b) => {
        return {
          reds: a.reds > b.reds ? a.reds : b.reds,
          greens: a.greens > b.greens ? a.greens : b.greens,
          blues: a.blues > b.blues ? a.blues : b.blues,
        }
      }))
    .map(result => result.reds * result.greens * result.blues)
    .map(peek)
    .reduce((a, b) => a + b, 0)
  console.log(result)
}

