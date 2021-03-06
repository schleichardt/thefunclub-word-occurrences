program for a meetup event of the fun club http://www.meetup.com/thefunclub/events/104441382/ 

# The task
You'll be given a simple textfile written in English language (like this one: http://www.textlibrary.com/download/moby-dic.txt).

Your task is to write a litte program that counts the occurences of words and print the 10 most frequent words with their number of occurences to stdout, like so (numbers are not correct!):

$ mysolution < moby-dic.txt
the: 50123
of: 10236
and: 9999
to: 4024
a: 3901
in: 2561
that: 2400
i: 2331
was: 2114
he: 1738

What is a word?
---------------------
- we'll assume that a word consists just of the the characters from a-z
- we don't distinguish uppercase and lowercase, so it's okay to convert everything to lowercase
- everything that is not in a-z can be considered a word boundary, so it's easiert for you to deal with commas, colons and the like.

What is the minimal requirement?
---------------------------------------
1. Write a minimal solution in your language that solves the task for the moby-dic.txt
2. Make your solution presentable (comment your source or prepare a little slide)
3. Be able to explain in a few sentences
- how your solution works
- what dependencies it has (non standard libraries etc)
- in what way your solution benefits from something special about your language
- and what its drawbacks are (if there are any)

What else can be done (optional)?
---------------------------------------------
Performance:
- Benchmark your solution in regards of time consumption. Either use the time command (man time) or even show us how to benchmark in your language.
- What is your solution spending time with? IO? Garbage collection?
- Improve that.
- Benchmark again...

Memory consumption:
- It is fine to read moby-dic.txt all at one into memory, but what if we give you a corpus that does exceed your machines memory? Fix this. Tell us about.
- Since this is functional programming: What datastructure did you use? Is it functional? Does it trigger heavy allocation and garbage collection? Find out and tell us about.

## Licence

This software is licensed under the Apache 2 license, quoted below.

Copyright 2013 schleichardt

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

# Packaging
`sbt one-jar`