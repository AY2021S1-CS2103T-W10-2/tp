---
layout: page
title: Caitlin Jee's Project Portfolio Page
---

## Project: Wishful Shrinking

Wishful Shrinking is a desktop application for managing your diet, keeping track of your on-hand ingredients, recipes, as well as the food you’ve eaten (along with their calories). While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Feature**: Added the ability to get recommended recipes.
  * What it does: allows the user to get recipes that they can make based on their fridge, that is the recipes whose ingredients are all present in their fridge.
  * Justification: This feature improves the product significantly because a user can easily search for what recipes they are able to make, which the ingredient that are already present in their fridge.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **New Feature**: Added the ability to add ingredients to the user's fridge.
  * What it does: allows the user to add ingredients to their fridge in Wishful Shrinking.
  * Justification: This feature improves the product significantly because a user can now save ingredients that they have into the fridge, which is essential in using the `recommend` recipes feature.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the "add contacts" feature in AddressBook Level 3 and refactored to add ingredients instead of contacts.
  
* **New Feature**: Added the ability to search for an ingredient in the fridge.
  * What it does: allows the user to search for an ingredient in the fridge by its name.
  * Justification: This feature improves the product significantly because a user can now easily search for an ingredient by its name to see whether it is present in the fridge.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the "search for contact by name" feature in AddressBook Level 3.
  
* **New Feature**: Added the ability to delete a consumed recipe in the consumption list.
  * What it does: allows the user to delete a recipe that they have eaten in the consumption list.
  * Justification: This feature improves the product significantly because a user can now delete the recipes that they do not want anymore or have mistakenly added from the consumption list.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the "delete contact" feature in AddressBook Level 3.  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=caitlinjee&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed release `v1.2` (1 release) on GitHub
  * Refactored code to add and support the Ingredient class when work began on the fridge related features
  * Updated the links and architecture sequence diagram, Logic, Model and Storage class diagrams in the Developer Guide to match Wishful Shrinking

* **Enhancements to existing features**:
  * Added support for tags for recipes (Pull requests [\#85](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/85))
    * Justification: This feature, which is built off of the existing tag feature in Address Book 3, allows users to easily tag their favourite recipes, in order to easily filter by them later.

  * Added the ability to search for a recipe in the recipe list by its ingredients or tags, in addition to name. (Pull requests [\#109](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/109))
    * Justification: This feature improves upon the original "search for contact by name" feature that was originally in AddressBook Level 3, and later implemented in Wishful Shrinking, as it now allows users to search for recipes not only by name, but also by the ingredients it contains, which is essential in recipes, and also tags, which are helpful for the user to filter their favourite recipes.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `searchR`, `recommend` and `deleteC` [\#110](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/110)
    * Added UI images [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178)
    * Did cosmetic tweaks to User Guide and ensured phrasing used was consistent and accurate: [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178)
  * Developer Guide:
    * Added implementation details of the `addF`, `deleteC`, `recommend` and `searchR` feature: [\#118](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/118/files)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117), [\#115](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/115), [\#175](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/175), [\#173](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/173)

