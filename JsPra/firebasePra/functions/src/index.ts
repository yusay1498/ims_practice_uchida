import * as z from "zod";

// Import the Genkit core libraries and plugins.
import {generate} from "@genkit-ai/ai";
import {configureGenkit} from "@genkit-ai/core";
import {firebase} from "@genkit-ai/firebase";

// From the Firebase plugin, import the functions needed to deploy flows using
// Cloud Functions.
import {firebaseAuth} from "@genkit-ai/firebase/auth";
import {onFlow} from "@genkit-ai/firebase/functions";
 
configureGenkit({
  plugins: [
    // Load the Firebase plugin, which provides integrations with several
    // Firebase services.
    firebase(),
  ],
  // Log debug output to tbe console.
  logLevel: "debug",
  // Perform OpenTelemetry instrumentation and enable trace collection.
  enableTracingAndMetrics: true,
});

// Define a simple flow that prompts an LLM to generate menu suggestions.
export const menuSuggestionFlow = onFlow(
  {
    name: "menuSuggestionFlow",
    inputSchema: z.string(),
    outputSchema: z.string(),
    authPolicy: firebaseAuth((user) => {
      // By default, the firebaseAuth policy requires that all requests have an
      // `Authorization: Bearer` header containing the user's Firebase
      // Authentication ID token. All other requests are rejected with error
      // 403. If your app client uses the Firebase Cloud Functions callable
      // functions feature, the library automatically attaches this header to
      // requests.

      // You should also set additional policy requirements as appropriate for
      // your app. For example:
      // if (!user.email_verified) {
      //   throw new Error("Verified email required to run flow");
      // }
    }),
  },
  async (subject) => {
		// Construct a request and send it to the model API.
    const prompt =
      `Suggest an item for the menu of a ${subject} themed restaurant`;
    const llmResponse = await generate({
      model: '' /* TODO: Set a model. */,
      prompt: prompt,
      config: {
        temperature: 1,
      },
    });

		// Handle the response from the model API. In this sample, we just
		// convert it to a string, but more complicated flows might coerce the
		// response into structured output or chain the response into another
		// LLM call, etc.
    return llmResponse.text();
  }
);
