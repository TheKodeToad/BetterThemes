# BetterThemes
A plugin to load editor colour themes in Eclipse. Uses a nice JSON format instead of XML, and has support for variables and CSS colours (using [csscolor4j](https://github.com/silentsoft/csscolor4j)).
Also uses a WeavingHook to work around [Bug 559321 - [Dark Theme] Custom editor background color not properly applied](https://bugs.eclipse.org/bugs/show_bug.cgi?id=559321).

If you want to use colour themes for Eclipse Color Theme, they can be easily imported. I'd recommend [this site](https://eclipse-color-themes.web.app/) for downloading them. Thanks to [ahatem](https://github.com/ahatem) for creating this archive!

## Screenshots

### Syntax themes

![image](https://user-images.githubusercontent.com/57493648/195991446-fcb5cea7-e9d7-42d6-a116-8754a744d217.png)
<br/>
Atom One Dark

<details>

<summary>More</summary>

![image](https://user-images.githubusercontent.com/57493648/195991477-f3f3364d-c4c3-49aa-8933-86017bf0b982.png)
<br/>
Visual Studio Dark+

![image](https://user-images.githubusercontent.com/57493648/195991498-80a58e57-f19b-4aca-a3e5-45670013445c.png)
<br/>
Solarized Dark

![image](https://user-images.githubusercontent.com/57493648/195991515-bffb129d-4ffa-4a47-b32d-8e269b8ab4d5.png)
<br/>
RainbowDrops

</details>

More stock themes will likely be added in future.

![image](https://user-images.githubusercontent.com/57493648/195991720-29d4f09b-3c36-4160-a046-2cb3cdb8143b.png)
<br/>
Preferences screen

## TODO
- [x] Syntax theming
- [x] Importing Eclipse Color Theme files.
- [ ] Apply to console (including ANSI).
- [ ] Apply to Javadoc and other hovers.
- [ ] UI theming
- [ ] Icon theming

Possibly in a separate plugin:

- [ ] Smooth scrolling
- [ ] Inline search
