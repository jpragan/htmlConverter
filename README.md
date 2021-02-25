#htmlConverter

1. The html converter tool is used to parse out words and definitiions from the given dictionary.html file. htmlConverter can be easily modified to parse out different values in the html file. 

2. Once the words and definitions have been parsed out of the source .html document, the converter will then build a JSON object formatted to batch insert the words and definitions into AWS DynamoDB. 

3. DynamoDB only allows a batch insert of 25 items at a time. htmlConverter will write the JSON obect into a .JSON file (Dictionary[page].JSON). The file will only have 25 items. 

4. Using AWS CLI (Command Line Interface) a loop can be run on the Dictionary.JSON files to batch insert into DynamoDB.


### input
#### Dictionary.html

``` html

<h1>Continuant</h1>
<Xpage=313>

<hw>Con*tin"u*ant</hw> <tt>(?)</tt>, <tt>a.</tt> <def>Continuing; prolonged; sustained; <as>as, a <ex>continuant</ex> sound</as>.</def> -- <def2><tt>n.</tt>  <def>A continuant sound; a letter whose sound may be prolonged.</def></def2>

<h1>Immute</h1>
<Xpage=733>

<hw>Im*mute"</hw> <tt>(?)</tt>, <tt>v. t.</tt> <ety>[L. <ets>immutare</ets>, <ets>immutatum</ets>; perf. <ets>im-</ets> in + <ets>mutare</ets> to change : cf. OF. <ets>immuter</ets>.]</ety> <def>To change or alter.</def> <mark>[Obs.]</mark>
```


### output
#### Dictionary3.JSON
``` JSON
{
    "Dictionary": [{
        "PutRequest": {
			"Item": {
				"Word": {
					"S": "Continuant"
				},
				"Definition": {
					"S": " A continuant sound; a letter whose sound may be prolonged."
				}
			}
		}
	}, {
		"PutRequest": {
			"Item": {
				"Word": {
					"S": "Immute"
				},
				"Definition": {
					"S": "To change or alter."
				}
			}
		}
	}]
}
```
