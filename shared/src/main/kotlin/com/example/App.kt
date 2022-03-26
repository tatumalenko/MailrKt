package com.example

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ojaynico.kotlin.react.native.api.AppRegistry
import ojaynico.kotlin.react.native.api.Linking
import ojaynico.kotlin.react.native.component.ImageBackground
import ojaynico.kotlin.react.native.component.SafeAreaView
import ojaynico.kotlin.react.native.component.ScrollView
import ojaynico.kotlin.react.native.component.StatusBar
import ojaynico.kotlin.react.native.component.Text
import ojaynico.kotlin.react.native.component.TouchableOpacity
import ojaynico.kotlin.react.native.component.View
import react.FC
import react.Props

val mainScope = MainScope()

val Header = FC<Props> {
  ImageBackground {
    source = kotlinext.js.require("./resources/logo.png")
    style = styles.background
    imageStyle = styles.logo

    Text {
      style = styles.text
      +"Welcome to React and Kotlin"
    }
  }
}

val App = FC<Props> {
  StatusBar {}
  SafeAreaView {
    ScrollView {
      contentInsetAdjustmentBehavior = "automatic"
      asDynamic().style = styles.scrollView
      Header {}
      View {
        style = styles.body
        View {
          style = styles.sectionContainer
          Text {
            style = styles.sectionTitle
            +"Step One"
          }
          Text {
            style = styles.sectionDescription
            +"Edit App.kt to change this screen, run 'yarn run gradleAssemble' OR 'npm run gradleAssemble' and then come back to see your edits."
          }
        }
        View {
          style = styles.sectionContainer
          Text {
            style = styles.sectionTitle
            +"See Your Changes"
          }
          Text {
            style = styles.sectionDescription
            +"Double tap R on your keyboard to reload your app's code."
          }
        }
        View {
          style = styles.sectionContainer
          Text {
            style = styles.sectionTitle
            +"Debug"
          }
          Text {
            style = styles.sectionDescription
            +"Press Cmd or Ctrl + M or Shake your device to open the React Native debug menu."
          }
        }
        View {
          style = styles.sectionContainer
          Text {
            style = styles.sectionTitle
            +"Learn More"
          }
          Text {
            style = styles.sectionDescription
            +"Read the docs to discover what to do next:"
          }
        }
        View {
          style = styles.container
          links.forEach { link ->
            View {
              style = styles.separator
            }
            TouchableOpacity {
              style = styles.linkContainer
              onPress = {
                mainScope.launch {
                  Linking.openURL(link.link)
                }
              }
              Text {
                style = styles.link
                +link.title
              }
              Text {
                style = styles.description
                +link.description
              }
            }
          }
        }
      }
    }
  }
}

fun main() {
  AppRegistry.registerComponent("MailrKt") { App }
}
