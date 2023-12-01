import * as fs from "fs";

export function getInput(day: string): string[] {
  const allFileContents = fs.readFileSync(`./input/${day}.txt`, "utf-8");
  return allFileContents.split(/\n/)
}

export function peek<T>(line: T): T {
  console.log(line);
  return line
}
